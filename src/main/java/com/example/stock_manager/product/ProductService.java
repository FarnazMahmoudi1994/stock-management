package com.example.stock_manager.product;

import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final StockRepository stockRepository;
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
               .orElseThrow(() -> new NotFoundException("","این محصول پیدا نشد"));
    }

    @Override
    public Integer getStockCount(Long id) {
        getById(id);
        return stockRepository.findStockCountByProductId(id);
    }

    @Override
    public Page<Product> paging(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page,size, Sort.by("id").descending()));
    }

}
