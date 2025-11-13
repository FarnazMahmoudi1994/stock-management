package com.example.stock_manager.product;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {

}