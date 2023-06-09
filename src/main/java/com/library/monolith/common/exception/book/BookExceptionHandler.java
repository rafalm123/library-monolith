package com.library.monolith.common.exception.book;

import com.library.monolith.common.exception.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleError(WebRequest request, Exception e) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareExceptionDto(
                e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),
                request.getContextPath(), ((ServletWebRequest) request).getHttpMethod().name()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = BookException.class)
    public ResponseEntity<ExceptionDto> handleBookException(BookException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getMessage());
        HttpStatus httpStatus = resolveStatus(e);
        return new ResponseEntity<>(prepareExceptionDto(
                e, httpStatus, e.getMessage(), request.getContextPath(),
                ((ServletWebRequest) request).getHttpMethod().name()), httpStatus);
    }

    private HttpStatus resolveStatus(BookException bookException) {
        return switch (bookException.getBookError()) {
            case BOOK_RELEASE_NOT_FOUND, SOME_OTHER_CASE_ERROR -> HttpStatus.NOT_FOUND;
        };
    }

    private String getEndpointData(WebRequest request) {
        return MessageFormat.format("[{0}] {1} Exception raised: ",
                ((ServletWebRequest) request).getHttpMethod().toString(),
                request.getDescription(false));
    }

    private String getEndpointData(String httpMethod, String description) {
        return MessageFormat.format("[{0}] {1} Exception raised: ", httpMethod, description);
    }

    private ExceptionDto prepareExceptionDto(Exception e, HttpStatus status, String message, String path, String method) {
        return ExceptionDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .message(message)
                .path(path)
                .method(method)
                .build();
    }
}
