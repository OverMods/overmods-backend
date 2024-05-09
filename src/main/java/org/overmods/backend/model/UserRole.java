package org.overmods.backend.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    MODDER,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
