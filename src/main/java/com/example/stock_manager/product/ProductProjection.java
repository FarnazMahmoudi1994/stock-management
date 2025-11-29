package com.example.stock_manager.product;

import java.math.BigDecimal;

public interface ProductProjection {

    Long getId();
    String getName();
    String getDescription();
    BigDecimal getPrice();
    //Integer getStockCount();
    StockInfo getStock();

    interface StockInfo {
        Integer getStockCount();
    }


}
