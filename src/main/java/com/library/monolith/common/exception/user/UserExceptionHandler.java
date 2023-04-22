package com.library.monolith.common.exception.user;

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
public class UserExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleError(HttpServletRequest request, Exception e) {
        log.error(getEndpointData(request.getMethod(), request.getRequestURL().toString()) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareExceptionDto(
                e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),
                request.getContextPath(), ((ServletWebRequest) request).getHttpMethod().name()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ExceptionDto> handleBookException(UserException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getMessage());
        HttpStatus httpStatus = resolveStatus(e);
        return new ResponseEntity<>(prepareExceptionDto(
                e, httpStatus, e.getMessage(), request.getContextPath(),
                ((ServletWebRequest) request).getHttpMethod().name()), httpStatus);
    }

    private HttpStatus resolveStatus(UserException userException) {
        return switch (userException.getUserError()) {
            case LIBRARY_USER_VERSION_NOT_FOUND, LIBRARY_USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case OTHER_ERROR -> null;
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
