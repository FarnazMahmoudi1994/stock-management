package com.example.stock_manager.stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends ListCrudRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);
    @Query("SELECT s.stockCount FROM Stock s WHERE s.product.id = :productId")
    Integer findStockCountByProductId(@Param("productId") Long productId);

}