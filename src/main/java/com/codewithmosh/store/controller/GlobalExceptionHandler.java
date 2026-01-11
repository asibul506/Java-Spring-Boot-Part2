package com.codewithmosh.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Exception handler for validation errors in request bodies. this method will be invoked automatically when a MethodArgumentNotValidException is thrown.
    @ExceptionHandler(MethodArgumentNotValidException.class) // Specify the exception type to handle
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ){
        var errors= new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        ); // Extract field errors and put them into the map

        return ResponseEntity.badRequest().body(errors);
    }

}
