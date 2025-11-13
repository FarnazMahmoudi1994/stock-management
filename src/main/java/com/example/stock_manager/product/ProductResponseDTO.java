package com.example.stock_manager.product;

import com.example.stock_manager.common.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO extends BaseDTO {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stockCount;
}
