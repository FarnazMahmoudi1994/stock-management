package com.example.stock_manager.transaction;

import com.example.stock_manager.product.IProductService;
import com.example.stock_manager.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository repository;
    private final IProductService productService;

    @Override
    public Transaction save(Transaction transaction) {
        Long productId = transaction.getProduct().getId();
        Product product = productService.getById(productId);
        transaction.setProduct(product);
        return repository.save(transaction);
    }


}
