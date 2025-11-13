package com.example.stock_manager.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final IStockService stockService;
    private final StockMapper stockMapper;


    @GetMapping("/{product_id}")
    public ResponseEntity<StockDTO> getByProductId(@PathVariable(required = false, name = "product_id") Long productId ){

        Stock stock = stockService.getByProductId(productId);
        StockDTO stockDTO =stockMapper.toStockDTO(stock);
        return ResponseEntity.ok(stockDTO);
    }


}
