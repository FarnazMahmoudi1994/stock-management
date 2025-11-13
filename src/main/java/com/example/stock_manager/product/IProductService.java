package com.example.stock_manager.product;

public interface IProductService {

    Product save(Product product);

    Product getById(Long id);
    Integer getStockCount(Long id);

}
