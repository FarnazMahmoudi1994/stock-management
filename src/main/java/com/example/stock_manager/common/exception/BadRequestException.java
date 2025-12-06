package com.example.stock_manager.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private String value;

    public BadRequestException(String value, String message) {
        super(message);
        this.value = value;
    }
}