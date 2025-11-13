package com.example.stock_manager.product;

import org.springframework.data.domain.Page;

public interface IProductService {

    Product save(Product product);

    Product getById(Long id);
    Integer getStockCount(Long id);

    Page<Product> paging(Integer page, Integer size);
}
