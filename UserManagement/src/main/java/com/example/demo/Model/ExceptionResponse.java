package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExceptionResponse {

    private String msg;
    private HttpStatus status;
}