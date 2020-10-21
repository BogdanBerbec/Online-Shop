package ro.bogdan.onlineshop.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//@Entity
//@Table(name = "users")
public class AppUser implements UserDetails {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "user_id")
    private final int userId;

//    @Column(name = "username")
    @NotBlank
    private final String username;

//    @Column(name = "password")
    @NotBlank
    private final String password;

//    @Column(name = "authorities")
    @NotBlank
    private final Set<SimpleGrantedAuthority> authorities;

//    @Column(name = "isAccountNonExpired")
    @NotBlank
    private final boolean isAccountNonExpired;

//    @Column(name = "isAccountNonLocked")
    @NotBlank
    private final boolean isAccountNonLocked;

//    @Column(name = "isCredentialsNonExpired")
    @NotBlank
    private final boolean isCredentialsNonExpired;

//    @Column(name = "isEnabled")
    @NotBlank
    private final boolean isEnabled;

    public AppUser(@JsonProperty("user_id") int userId,
                   @JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("authorities") Set<SimpleGrantedAuthority> authorities,
                   @JsonProperty("isAccountNonExpired") boolean isAccountNonExpired,
                   @JsonProperty("isAccountNonLocked") boolean isAccountNonLocked,
                   @JsonProperty("isCredentialsNonExpired") boolean isCredentialsNonExpired,
                   @JsonProperty("isEnabled") boolean isEnabled) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
