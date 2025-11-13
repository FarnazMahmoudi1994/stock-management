package com.example.stock_manager.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping
    public ResponseEntity purchaseProduct(@RequestBody TransactionDTO transactionDTO){

        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
        transactionService.purchaseProduct(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
