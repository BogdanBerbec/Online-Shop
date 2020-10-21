package ro.bogdan.onlineshop.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AppUserMySQL implements AppUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppUserMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppUser> selectAllAppUsers() {
        String sql = "" +
                "SELECT " +
                " user_id, " +
                " username, " +
                " password, " +
                " authorities, " +
                " is_account_non_expired, " +
                " is_account_non_locked, " +
                " is_credentials_non_expired, " +
                " is_credentials_non_expired " +
                "FROM users";
        return jdbcTemplate.query(sql, mapAppUserFromDb());
    }

    private RowMapper<AppUser> mapAppUserFromDb() {
        return (resultSet, i) -> {
            int userId = resultSet.getInt("user_id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            System.out.println(resultSet.getString("authorities"));
//            Set<String> authoritiesStr = resultSet.getObject("authorities", Set.class);
//            Set<SimpleGrantedAuthority> authorities = authoritiesStr.stream()
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toSet());
            String authoritiesStr = resultSet.getString("authorities");
            Set<SimpleGrantedAuthority> authorities = Arrays.stream(authoritiesStr.split(", "))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());

            boolean isAccountNonExpired = resultSet.getBoolean("is_account_non_expired");
            boolean isAccountNonLocked = resultSet.getBoolean("is_account_non_locked");
            boolean isCredentialsNonExpired = resultSet.getBoolean("is_credentials_non_expired");
            boolean isEnabled = resultSet.getBoolean("is_credentials_non_expired");

            return new AppUser(
                    userId, username, password, authorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
            );
        };
    }

    @Override
    public Optional<AppUser> selectAppUserById(int id) {
        return selectAllAppUsers().stream()
                .filter(appUser -> appUser.getUserId() == id)
                .findFirst();
    }

    @Override
    public int insertAppUser(AppUser appUser) {
        String sql = "" +
                "INSERT INTO users (" +
                " user_id, " +
                " username, " +
                " password, " +
                " authorities, " +
                " is_account_non_expired, " +
                " is_account_non_locked, " +
                " is_credentials_non_expired, " +
                " is_enabled) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                appUser.getUserId(),
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getAuthorities().toString().substring(1, appUser.getAuthorities().toString().length()-1),
                appUser.isAccountNonExpired(),
                appUser.isAccountNonLocked(),
                appUser.isCredentialsNonExpired(),
                appUser.isEnabled()
        );
    }

    @Override
    public int updateAppUser(int id, AppUser appUser) {
        String sql = "" +
                "UPDATE users SET" +
                " username = ?, " +
                " password = ?, " +
                " authorities = ?, " +
                " is_account_non_expired = ?, " +
                " is_account_non_locked = ?, " +
                " is_credentials_non_expired = ?, " +
                " is_enabled = ?";
        return jdbcTemplate.update(
                sql,
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getAuthorities().toString().substring(1, appUser.getAuthorities().toString().length()-1),
                appUser.isAccountNonExpired(),
                appUser.isAccountNonLocked(),
                appUser.isCredentialsNonExpired(),
                appUser.isEnabled()
        );
    }

    @Override
    public int deleteAppUser(int id) {
        return 0;
    }
}
