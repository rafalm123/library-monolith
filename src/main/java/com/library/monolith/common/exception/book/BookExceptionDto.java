package com.library.monolith.common.exception.book;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class BookExceptionDto {
    private LocalDateTime timeStamp;
    private int status;
    private String message;
    private String path;
    private String method;
}