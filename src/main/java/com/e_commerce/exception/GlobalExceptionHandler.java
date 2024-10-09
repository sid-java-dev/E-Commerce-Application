package com.e_commerce.exception;

import com.e_commerce.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OtpStorageException.class)
    public ResponseEntity<ErrorDetails> handleOtpStorageException(OtpStorageException e, WebRequest webRequest) {
        ErrorDetails errorDetails=new ErrorDetails(e.getMessage(),new Date(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }


    @ExceptionHandler(OtpVerificationException.class)
    public ResponseEntity<ErrorDetails> handleOtpVerificationException(OtpVerificationException e ,WebRequest webRequest) {
        ErrorDetails errorDetails=new ErrorDetails(e.getMessage(),new Date(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ){
        Map<String,String>errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

