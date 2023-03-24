package com.library.monolith.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookException extends RuntimeException {
    private BookError bookError;
}
