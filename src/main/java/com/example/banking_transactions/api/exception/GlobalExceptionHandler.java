package com.example.banking_transactions.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
//Global exception handling for all controllers.
@ControllerAdvice
public class GlobalExceptionHandler {
    //Handle exceptions related to Validation error.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        //Create a map to store field errors.
        Map<String, String> errors = new HashMap<>();
        //Iterate over each validation error
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            //Get field name for the failed validation
            String fieldName = ((FieldError) error).getField();
            //Get validation error message.
            String errorMessage = error.getDefaultMessage();
            //Add field name and error message.
            errors.put(fieldName, errorMessage);
        });
        //Return map containing field validation errors.
        return errors;
    }
    //Handles all generic exceptions not covered by specific handlers.
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleAllException(Exception ex) {
        //Create a map to store the errors.
        Map<String, String> error = new HashMap<>();
        //Adds exception messages.
        error.put("message", ex.getMessage());
        //Returns the map containing the error message.
        return error;
    }

}

