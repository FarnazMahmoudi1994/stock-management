package com.example.stock_manager.transaction;

import com.example.stock_manager.common.exception.BadRequestException;
import com.example.stock_manager.common.exception.InsufficientStockException;
import com.example.stock_manager.product.Product;
import com.example.stock_manager.product.ProductRepository;
import com.example.stock_manager.stock.StockRepository;
import com.example.stock_manager.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository repository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    private final StockService stockService;

    @Transactional
    @Override
    public Transaction purchaseProduct(Transaction transaction, Boolean isReserved) {

        Long productId = transaction.getProduct().getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadRequestException("Product not found with id: " + productId));



        transaction.setProduct(product);
        Transaction savedTransaction = repository.save(transaction);
        Integer quantity= transaction.getQuantity();

        switch (transaction.getType()) {
            case IN ->  increaseStock(product, quantity);
            case OUT -> {
                if(!isReserved && stockService.getSalableInventory(productId) < transaction.getQuantity()){
                    throw new InsufficientStockException("", "موجودی قابل فروش کافی نیست.");
                }
                processDirectSale(product, quantity);
            }
        }

        return savedTransaction;
    }


    public void processDirectSale(Product product,Integer quantity){
        Integer newStockCount = product.getStock().getStockCount() - quantity;
        product.getStock().setStockCount(newStockCount);
        stockRepository.save(product.getStock());
    }


    public void increaseStock(Product product, Integer quantity){
        Integer newStockCount = product.getStock().getStockCount() + quantity;
        product.getStock().setStockCount(newStockCount);
        stockRepository.save(product.getStock());
    }

}
