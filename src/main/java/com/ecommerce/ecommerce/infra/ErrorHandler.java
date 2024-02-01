package com.ecommerce.ecommerce.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.IllegalArgumentException;
import java.util.HashMap;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> teste() {
        HashMap<String, Object> response = new HashMap<>();

        response.put("error", true);
        response.put("message", "Os dados recebidos não são válidos");

        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> validationExceptions(MethodArgumentNotValidException data) {

        return ResponseEntity
            .status(400)
            .body(data.getAllErrors().stream().map(item -> item.getDefaultMessage()));
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Object> genericException() {
    //     HashMap<String, Object> response = new HashMap<>();

    //     response.put("error", true);
    //     response.put("message", "Ocorreu um erro desconhecido");

    //     return ResponseEntity.status(500).body(response);
    // }
}
