package com.microservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleInventoryNotFound(
            InventoryNotFoundException ex
    ) {


        ErrorResponse response =
                new ErrorResponse(
                        404,
                        ex.getMessage(),
                        java.time.LocalDateTime.now()
                );


        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
    }
