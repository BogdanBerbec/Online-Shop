package ro.bogdan.onlineshop.security;

public enum AppUserAuthorities {
    BUY_PRODUCT("buy:product"),
    WRITE_PRODUCT("write:product"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String authority;

    AppUserAuthorities(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
