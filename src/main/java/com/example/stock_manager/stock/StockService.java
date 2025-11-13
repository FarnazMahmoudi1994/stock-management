package com.example.stock_manager.stock;

import com.example.stock_manager.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {

    private final StockRepository repository;

    @Override
    public Stock getByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException("","not found"));
    }

    @Override
    public Stock getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("stock","not found"));
    }

    @Override
    public Stock update(Stock stock) {
        Stock lastStock = getById(stock.getId());
        lastStock.setStockCount(stock.getStockCount());
        lastStock.setProduct(stock.getProduct());
        return repository.save(lastStock);
    }


}
