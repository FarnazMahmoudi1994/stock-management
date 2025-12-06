package com.example.stock_manager.common.exception;

import com.example.stock_manager.common.Error;
import com.example.stock_manager.common.ResponseHandler;
import com.example.stock_manager.common.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleInsufficientStockException(InsufficientStockException ex) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(new Error(Collections.singletonList(ex.getValue()), Collections.singletonList(ex.getMessage())));
        return ResponseHandler.generateResponseError(HttpStatus.BAD_REQUEST, true, "1001e", error);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(
                new Error(Collections.singletonList(ex.getValue()), Collections.singletonList(ex.getMessage()))
        );

        return ResponseHandler.generateResponseError(HttpStatus.NOT_FOUND, true, "1002e", error);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(
                new Error(Collections.singletonList(ex.getValue()), Collections.singletonList(ex.getMessage()))
        );

        return ResponseHandler.generateResponseError(HttpStatus.CONFLICT, true, "1003e", error);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(
                new Error(Collections.singletonList(ex.getValue()), Collections.singletonList(ex.getMessage()))
        );

        return ResponseHandler.generateResponseError(HttpStatus.BAD_REQUEST, true, "1004e", error);
    }


}

