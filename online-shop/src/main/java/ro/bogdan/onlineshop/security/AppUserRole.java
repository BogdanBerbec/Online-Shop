package ro.bogdan.onlineshop.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ro.bogdan.onlineshop.security.AppUserAuthorities.*;

public enum AppUserRole {
    USER(Sets.newHashSet(BUY_PRODUCT)),
    ADMIN(Sets.newHashSet(BUY_PRODUCT, WRITE_PRODUCT, ADMIN_READ)),
    MANAGER(Sets.newHashSet(BUY_PRODUCT, WRITE_PRODUCT, ADMIN_READ, ADMIN_WRITE));

    private final Set<AppUserAuthorities> authorities;

    AppUserRole(Set<AppUserAuthorities> authorities) {
        this.authorities = authorities;
    }

    public Set<AppUserAuthorities> getAuthorities() {
        return authorities;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
