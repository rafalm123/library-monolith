package com.library.monolith.common.config;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.library.monolith.common.config.LibraryUserPermission.*;

@Getter
public enum UserRole {
    ROLE_REGULAR(Sets.newHashSet(REGULAR_READ)),
    ROLE_ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, REGULAR_READ));
    private final Set<LibraryUserPermission> permissions;


    UserRole(Set<LibraryUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE " + this.name()));
        return permissions;
    }

}
