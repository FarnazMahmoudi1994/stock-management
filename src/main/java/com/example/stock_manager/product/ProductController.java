package com.example.stock_manager.product;

import com.example.stock_manager.common.ResponseHandler;
import com.example.stock_manager.stock.StockCountProjection;
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
    public ResponseEntity<ProductProjection> getById(@PathVariable Long id ){
        ProductProjection productProjection = productService.getByProductId(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, false, "1000", productProjection);
    }

    @GetMapping("/get_stock_count/{id}")
    public ResponseEntity<StockCountProjection> getStockCountById(@PathVariable(required = false, name = "id") Long id ){
        StockCountProjection stockCountProjection = productService.getStockCount(id);
        return ResponseEntity.ok(stockCountProjection);
    }


}
