package com.data.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler implements ValidationException2 {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request){
        Map<String, String> errors= new HashMap<>();
        for(ObjectError err : ex.getBindingResult().getAllErrors()){
            String fieldName = ((FieldError)err).getField();
            String value = err.getDefaultMessage();
            errors.put(fieldName, value);

        }
        return ResponseEntity.badRequest().body(errors);
    }
}
