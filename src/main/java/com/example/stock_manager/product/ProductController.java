package com.example.stock_manager.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;


    @PostMapping
    public ResponseEntity save(@RequestBody ProductRequestDTO productRequestDTO){

        Product product = productMapper.toProduct(productRequestDTO);
        productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable(required = false, name = "id") Long id ){

        Product product = productService.getById(id);
        Integer stockCount = productService.getStockCount(id);
        ProductResponseDTO productResponseDTO = productMapper.toProductResponseDTO(product);
        productResponseDTO.setStockCount(stockCount);
        return ResponseEntity.ok(productResponseDTO);
    }


}
