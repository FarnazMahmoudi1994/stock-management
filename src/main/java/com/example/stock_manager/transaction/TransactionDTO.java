package com.example.stock_manager.transaction;

import com.example.stock_manager.common.BaseDTO;
import lombok.Data;

@Data
public class TransactionDTO extends BaseDTO {

    private Type type;

    private Integer quantity;

    private Long productId;
}
