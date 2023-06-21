package com.MovieBookingApp.MovieBookingApp.Execption;

import com.MovieBookingApp.MovieBookingApp.Model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(SampleException.class)
    public ResponseEntity<ExceptionResponse> sample(SampleException e ){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(),HttpStatus.UNAUTHORIZED), HttpStatus.OK);

    }


    @ExceptionHandler(MovieNullException.class)
    public ResponseEntity<ExceptionResponse> MovieNull(MovieNullException e ){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage(),HttpStatus.UNAUTHORIZED), HttpStatus.OK);

    }
}
