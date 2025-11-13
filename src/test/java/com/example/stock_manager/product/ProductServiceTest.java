package com.example.stock_manager.product;

import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.stock.Stock;
import com.example.stock_manager.stock.StockRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProductService underTest;

    private Stock stock;
    private Product product;

    @BeforeEach
    public void init() {

        BigDecimal amount = BigDecimal.valueOf(1234.56);

        product = Product.builder()
                .id(1L)
                .name("product1")
                .price(amount)
                .description("des1")
                .build();

        stock = Stock.builder()
                .id(1L)
                .stockCount(100)
                .product(product)
                .build();
    }

    @Test
    void save() {
        //given
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        //when
        Product savedProduct = underTest.save(product);

        //then
        Assertions.assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    void getById_success() {
        //given
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        //when
        var response = underTest.getById(1L);
        //then
        assertEquals(product, response);
    }

    @Test
    void getById_notFound() {
        //given
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        var response = assertThatThrownBy(() -> underTest.getById(1L));
        //then
        response.isInstanceOf(NotFoundException.class)
                .hasMessageContaining("product not found","");
    }

    @Test
    void getStockCount() {
        //given
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(stockRepository.findStockCountByProductId(1L)).thenReturn(stock.getStockCount());

        //when
        Integer result = underTest.getStockCount(1L);

        //then
        assertEquals(result, stock.getStockCount());
    }

    @Test
    void getStockCount_notFound() {
        //given
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(stockRepository.findStockCountByProductId(1L)).thenReturn(null);

        //when
        var response = assertThatThrownBy(() -> underTest.getStockCount(1L));

        //then
        response.isInstanceOf(NotFoundException.class)
                .hasMessageContaining("stock not found for product","");    }
}