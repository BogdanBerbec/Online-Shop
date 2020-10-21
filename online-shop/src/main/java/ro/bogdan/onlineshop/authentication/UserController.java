package ro.bogdan.onlineshop.authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public void updateStudent(@RequestBody AppUser appUser, int id) {
        appUserService.updateUser(id, appUser);
    }
}
