package com.example.stock_manager.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.stock WHERE p.id = :id")
    ProductProjection findByProductId(@Param("id") Long id);

    /*@Query("SELECT p.id as id, p.name as name, p.description as description, p.price as price," +
            " s.stockCount as stockCount FROM Product p JOIN p.stock s WHERE p.id = :id")
    ProductProjection findByProductId(@Param("id") Long id);*/

}