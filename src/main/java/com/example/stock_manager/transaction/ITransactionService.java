package com.example.stock_manager.transaction;

public interface ITransactionService {

    Transaction purchaseProduct(Transaction transaction, Boolean isReserved);

}
