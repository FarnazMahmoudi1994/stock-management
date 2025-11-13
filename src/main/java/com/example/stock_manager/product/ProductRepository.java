package com.example.stock_manager.product;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, ListCrudRepository<Product, Long> {

}