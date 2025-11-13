package com.example.stock_manager.stock;

import com.example.stock_manager.common.exception.NotFoundException;
import com.example.stock_manager.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService underTest;

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
    void getByProductId_Success() {
        //given
        when(stockRepository.findByProductId(1L)).thenReturn(Optional.of(stock));
        //when
        var response = underTest.getByProductId(1L);
        //then
        assertEquals(stock, response);
    }

    @Test
    void getByProductId_notFound() {
        //given
        when(stockRepository.findByProductId(1L)).thenReturn(Optional.empty());
        //when
        var response = assertThatThrownBy(() -> underTest.getByProductId(1L));
        //then
        response.isInstanceOf(NotFoundException.class)
                .hasMessageContaining("not found","");
    }

    @Test
    void getById_success() {
        //given
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        //when
        var response = underTest.getById(1L);
        //then
        assertEquals(stock, response);
    }

    @Test
    void getById_notFound() {
        //given
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        var response = assertThatThrownBy(() -> underTest.getById(1L));
        //then
        response.isInstanceOf(NotFoundException.class)
                .hasMessageContaining("not found","stock");
    }

    @Test
    void update_Success() {

        //given
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock updatedStock = Stock.builder()
                .id(stock.getId())
                .stockCount(250)
                .product(product)
                .build();

        given(stockRepository.findById(updatedStock.getId())).willReturn(Optional.of(stock));

        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);

        //when
        Stock result = underTest.update(stock);

        //then
        assertNotNull(updatedStock);

        assertEquals(result, updatedStock);
    }
}