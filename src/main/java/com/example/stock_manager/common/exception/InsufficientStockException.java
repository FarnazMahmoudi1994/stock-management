package com.example.stock_manager.common.exception;

import lombok.Data;
@Data
public class InsufficientStockException extends RuntimeException {

    private String value;
    public InsufficientStockException(String value, String message) {
        super(message);
        this.value = value;
    }
}