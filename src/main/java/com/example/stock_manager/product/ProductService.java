package com.example.stock_manager.product;

import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final StockRepository stockRepository;

    @Transactional
    @Override
    public Product save(Product product) {
        Stock stock = Stock.builder()
                .stockCount(100)
                .product(product)
                .build();
        product.setStock(stock);
       return repository.save(product);

    }

    @Override
    public Product getById(Long id) {
       return repository.findById(id)
               .orElseThrow(() -> new NotFoundException("","product not found"));
    }

    @Override
    public Integer getStockCount(Long id) {
        getById(id);
        Integer stockCount =  stockRepository.findStockCountByProductId(id);
        if (stockCount == null) {
            throw new NotFoundException(String.valueOf(id), "stock not found for product");
        }
        return stockCount;
    }


}
