package com.example.stock_manager.transaction;

import com.example.stock_manager.product.IProductService;
import com.example.stock_manager.product.Product;
import com.example.stock_manager.stock.IStockService;
import com.example.stock_manager.stock.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository repository;
    private final IProductService productService;
    private final IStockService stockService;

    @Transactional
    @Override
    public Transaction purchaseProduct(Transaction transaction) {
        Long productId = transaction.getProduct().getId();
        Product product = productService.getById(productId);

        Integer stockCount = product.getStock().getStockCount();
        Stock stock = product.getStock();
        Integer requestQuantity= transaction.getQuantity();

        switch (transaction.getType()) {
            case IN ->  increaseStock(stock, stockCount, requestQuantity);
            case OUT -> {
                if(stockCount < transaction.getQuantity()){
                    throw new IllegalArgumentException();
                }
                decreaseStock(stock, stockCount, requestQuantity);
            }
        }

        //save transaction
        transaction.setProduct(product);
        return repository.save(transaction);
    }


    public void decreaseStock(Stock stock, Integer stockCount, Integer requestQuantity){
        Integer newStockCount = stockCount - requestQuantity;
        stock.setStockCount(newStockCount);
        stockService.update(stock);
    }


    public void increaseStock(Stock stock, Integer stockCount, Integer requestQuantity){
        Integer newStockCount = stockCount + requestQuantity;
        stock.setStockCount(newStockCount);
        stockService.update(stock);
    }

}
