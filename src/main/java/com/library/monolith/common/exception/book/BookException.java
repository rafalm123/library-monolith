package com.library.monolith.common.exception.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookException extends RuntimeException {
    private BookError bookError;
}
