package com.example.stock_manager.product;

import com.example.stock_manager.common.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO extends BaseDTO {

    @NotBlank
    private String name;

    @Schema(maxLength = 10000)
    private String description;

    @NotNull
    private BigDecimal price;
}
