package com.example.stock_manager.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;
    private final TransactionMapper transactionMapper;


}
