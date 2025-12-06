package com.example.stock_manager.product;

import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockCountProjection;
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
                .stockCount(0)
                .reservedCount(0)
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
    public StockCountProjection getStockCount(Long id) {
        getById(id);
        StockCountProjection stockCount =  stockRepository.findStockCountProjectionByProductId(id);
        if (stockCount == null) {
            throw new NotFoundException(String.valueOf(id), "stock not found for product");
        }
        return stockCount;
    }

    @Override
    public ProductProjection getByProductId(Long id) {
        ProductProjection productProjection = repository.findByProductId(id);
        if (productProjection == null) {
            throw new NotFoundException("", "product not found");
        }
        return productProjection;
    }


}
