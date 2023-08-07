package com.masaigai.springcodeconverterApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeConversionException.class)
    public ResponseEntity<String> CodeConversionHandler(CodeConversionException codeConversionException){
        return new ResponseEntity<>(codeConversionException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> CodeConversionHandler(Exception codeConversionException){
        return new ResponseEntity<>(codeConversionException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
