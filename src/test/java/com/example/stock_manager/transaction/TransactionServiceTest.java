package com.example.stock_manager.transaction;

import com.example.stock_manager.product.IProductService;
import com.example.stock_manager.product.Product;
import com.example.stock_manager.product.ProductRepository;
import com.example.stock_manager.product.ProductService;
import com.example.stock_manager.stock.IStockService;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private IStockService stockService;

    @Mock
    private IProductService productService;

    @InjectMocks
    private TransactionService underTest;

    private Stock stock;
    private Product product;
    private Transaction transaction;

    @BeforeEach
    public void init() {

        BigDecimal amount = BigDecimal.valueOf(1234.56);

        stock = Stock.builder()
                .id(1L)
                .stockCount(100)
                .build();

        product = Product.builder()
                .id(1L)
                .name("product1")
                .price(amount)
                .description("des1")
                .stock(stock)
                .build();



    }

    @Test
    void purchaseProductWhenTypeIsIN() {

        //given
        transaction = Transaction.builder()
                .id(1L)
                .product(product)
                .quantity(10)
                .type(Type.IN)
                .build();

        when(productService.getById(1L)).thenReturn(product);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        //when
        Transaction saved = underTest.purchaseProduct(transaction);
        assertNotNull(saved);
        assertEquals(110, stock.getStockCount());
    }

    @Test
    void purchaseProductWhenTypeIsOUT() {

        //given
        transaction = Transaction.builder()
                .id(1L)
                .product(product)
                .quantity(10)
                .type(Type.OUT)
                .build();

        when(productService.getById(1L)).thenReturn(product);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        //when
        Transaction saved = underTest.purchaseProduct(transaction);
        assertNotNull(saved);
        assertEquals(90, stock.getStockCount());
    }

    @Test
    void decreaseStock() {
    }

    @Test
    void increaseStock() {
    }
}