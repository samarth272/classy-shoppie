package com.microservices.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex
    ){

        ErrorResponse response =
                new ErrorResponse(
                        404,
                        ex.getMessage(),
                        LocalDateTime.now(),
                        null
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );


        ErrorResponse response =
                new ErrorResponse(
                        400,
                        "Validation failed",
                        LocalDateTime.now(),
                        errors
                );


        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }



    // Add this for debugging
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex
    ){

        ErrorResponse response =
                new ErrorResponse(
                        500,
                        ex.getMessage(),
                        LocalDateTime.now(),
                        null
                );


        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}