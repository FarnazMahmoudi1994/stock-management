package com.example.stock_manager.transaction;

import com.example.stock_manager.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface TransactionMapper {

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    Transaction toTransaction(TransactionDTO TransactionDTO);

    TransactionDTO toTransactionDTO(Transaction Transaction);

    List<TransactionDTO> toTransactionDTOS(List<Transaction> Transactions);

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    List<Transaction> toTransactions(List<TransactionDTO> TransactionDTOS);

}
