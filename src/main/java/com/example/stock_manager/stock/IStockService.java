package com.example.stock_manager.stock;

public interface IStockService {

    Stock getByProductId(Long productId);

    Stock getById(Long id);

    Stock update(Stock stock);

}
