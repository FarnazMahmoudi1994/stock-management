package com.example.stock_manager.transaction;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}