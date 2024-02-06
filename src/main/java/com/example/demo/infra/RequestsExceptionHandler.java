package com.example.demo.infra;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;


@RestControllerAdvice
public class RequestsExceptionHandler {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Map<String, String>> internalServerError500() {
        return ResponseEntity
            .status(500)
            .body(Collections.singletonMap("message", "Ocorreu um erro desconhecido no servidor"));
    }
}
