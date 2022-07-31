package com.house.talk.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ErrorExceptionController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("Http Request Method Not Supported Exception : {}", exception);

        return ErrorResponse.builder()
                .messasge(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {
        log.error("Method Argument Not Valid Exception url : {}, {}", httpServletRequest.getRequestURI(), exception.getStackTrace());

        return ErrorResponse.fromBindingException(exception.getBindingResult());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleInternalServerError(Exception exception) {
        log.error("Internel Server Error : {}", exception);

        return ErrorResponse.builder()
                .messasge(exception.toString())
                .build();
    }
}
