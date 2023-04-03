package com.library.monolith.common.config;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static com.library.monolith.common.config.LibraryUserPermission.*;

@Getter
public enum LibraryUserRoles {
    READER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, READER_READ, READER_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(ADMIN_READ, READER_READ));

    private final Set<LibraryUserPermission> permissions;

    LibraryUserRoles(Set<LibraryUserPermission> permissions) {
        this.permissions = permissions;
    }



}
