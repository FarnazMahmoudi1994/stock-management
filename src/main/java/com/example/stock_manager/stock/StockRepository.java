package com.example.stock_manager.stock;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends ListCrudRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);
    StockCountProjection findStockCountProjectionByProductId(@Param("productId") Long productId);

}