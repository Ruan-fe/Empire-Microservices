package com.saleservice.configurations.exceptions.handler;

import com.saleservice.configurations.exceptions.ResourceNotFoundException;
import com.saleservice.configurations.exceptions.models.StandardError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<StandardError> throwableDefault(Throwable e, HttpServletRequest request){
        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        StandardError err =  StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timeStamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
