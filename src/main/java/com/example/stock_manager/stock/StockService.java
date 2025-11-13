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
                .orElseThrow(() -> new NotFoundException("","موجود نیست"));
    }


}
