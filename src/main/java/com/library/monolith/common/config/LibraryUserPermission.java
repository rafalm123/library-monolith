package com.library.monolith.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LibraryUserPermission {
        REGULAR_READ("reader:read"),
        REGULAR_WRITE("reader:write"),
        ADMIN_READ("registry:read"),
        ADMIN_WRITE("registry:write");

        private final String permission;

}
