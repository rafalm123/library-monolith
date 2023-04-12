package com.library.monolith.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LibraryUserPermission {
        REGULAR_READ("regular:read"),
        ADMIN_READ("registry:read"),
        ADMIN_WRITE("registry:write");

        private final String permission;

}
