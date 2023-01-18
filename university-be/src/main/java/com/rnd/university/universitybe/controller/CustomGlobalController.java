package com.rnd.university.universitybe.controller;

import com.rnd.university.universitybe.exception.CustomErrorException;
import com.rnd.university.universitybe.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomGlobalController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<RestResponse> handleRequestException(CustomErrorException e, WebRequest request){
        RestResponse response = new RestResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        response.setData(null);
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
