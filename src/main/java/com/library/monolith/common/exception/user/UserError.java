package com.library.monolith.common.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserError {

    USER_VERSION_NOT_FOUND("User version with given ID does not exist."),
    OTHER_ERROR("empty for now");

    private final String message;

}
