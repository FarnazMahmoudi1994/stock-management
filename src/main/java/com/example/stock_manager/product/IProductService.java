package com.example.stock_manager.product;

import com.example.stock_manager.stock.StockCountProjection;

public interface IProductService {

    Product save(Product product);

    Product getById(Long id);
    StockCountProjection getStockCount(Long id);

    ProductProjection getByProductId( Long id);


}
