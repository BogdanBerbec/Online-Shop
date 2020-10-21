package ro.bogdan.onlineshop.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserDao appUserDao;

    @Autowired
    public AppUserService(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserDao.selectAllAppUsers()
                .stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public List<AppUser> getAllUsers() {
        return appUserDao.selectAllAppUsers();
    }

    public int insertUser(AppUser appUser) {
        return appUserDao.insertAppUser(appUser);
    }

    public Optional<AppUser> selectUserByUserId(int id) {
        return appUserDao.selectAppUserById(id);
    }

    public int updateUser(int id, AppUser appUser) {
        Optional<AppUser> optionalAppUser = selectUserByUserId(id);
        if (optionalAppUser.isPresent()) {
            return appUserDao.updateAppUser(id, appUser);
        }
        throw new NotFoundException(String.format("user %s not found", id));
    }

    public int removeUser(int id) {
        int userId = selectUserByUserId(id)
                .map(AppUser::getUserId)
                .orElseThrow(() -> new NotFoundException(String.format("user %s not found", id)));
        return appUserDao.deleteAppUser(id);
    }
}
