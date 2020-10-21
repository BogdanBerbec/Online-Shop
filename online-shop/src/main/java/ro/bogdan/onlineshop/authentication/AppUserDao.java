package ro.bogdan.onlineshop.authentication;

import java.util.List;
import java.util.Optional;

public interface AppUserDao {

    List<AppUser> selectAllAppUsers();

    Optional<AppUser> selectAppUserById(int id);

    int insertAppUser(AppUser appUser);

    int updateAppUser(int id, AppUser appUser);

    int deleteAppUser(int id);
}
