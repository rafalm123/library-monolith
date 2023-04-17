package com.library.monolith.common.exception.book;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookError {
    BOOK_RELEASE_NOT_FOUND("Book release with given ID does not exist."),
    SOME_OTHER_CASE_ERROR("this is just placeholder for future exceptions");

    private final String message;
}
