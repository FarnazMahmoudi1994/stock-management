package com.example.stock_manager.stock;

import com.example.stock_manager.common.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private Integer stockCount;

    @NotNull
    private Long productId;
}
