package com.example.stock_manager.transaction;

import com.example.stock_manager.common.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDTO extends BaseDTO {

    @NotNull
    private Type type;

    private Long productId;
}
