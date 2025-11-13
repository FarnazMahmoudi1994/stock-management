package com.example.stock_manager.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toProductResponseDTO(Product product);

    List<ProductResponseDTO> toProductResponseDTOS(List<Product> products);

    List<Product> toProducts(List<ProductRequestDTO> productRequestDTOS);

}
