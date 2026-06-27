package com.microservices.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<?> handleOrderException(OrderException ex){

        return new ResponseEntity<>(
                Map.of(
                        "message",ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
