package com.MovieBookingApp.MovieBookingApp.Execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="Exception handled by Custom exception")
public class CustomException extends  Exception{
}
