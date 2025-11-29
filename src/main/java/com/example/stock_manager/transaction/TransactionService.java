package com.example.stock_manager.transaction;

import com.example.stock_manager.product.Product;
import com.example.stock_manager.product.ProductRepository;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository repository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Transaction purchaseProduct(Transaction transaction) {

        Long productId = transaction.getProduct().getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
        transaction.setProduct(product);
        Transaction savedTransaction = repository.save(transaction);
        Stock stock = product.getStock();
        Integer stockCount = stock.getStockCount();
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

        return savedTransaction;
    }


    public void decreaseStock(Stock stock, Integer stockCount, Integer requestQuantity){
        Integer newStockCount = stockCount - requestQuantity;
        stock.setStockCount(newStockCount);
        stockRepository.save(stock);
    }


    public void increaseStock(Stock stock, Integer stockCount, Integer requestQuantity){
        Integer newStockCount = stockCount + requestQuantity;
        stock.setStockCount(newStockCount);
        stockRepository.save(stock);
    }

}
