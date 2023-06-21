package com.example.demo.Exception;

import com.example.demo.Model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionResponse> sample(UserNotFound e ){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.OK);

    }
}