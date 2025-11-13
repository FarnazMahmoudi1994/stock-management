package com.example.stock_manager.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private String value;
    private String message;

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}