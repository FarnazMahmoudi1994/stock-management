package com.example.stock_manager.stock;

import com.example.stock_manager.common.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockDTO extends BaseDTO {

    @NotNull
    private Integer stockCount;

    @NotNull
    private Long productId;
}
