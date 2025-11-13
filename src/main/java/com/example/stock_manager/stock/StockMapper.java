package com.example.stock_manager.stock;

import com.example.stock_manager.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StockMapper {

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    Stock toStock(StockDTO stockDTO);

    StockDTO toStockDTO(Stock stock);

    List<StockDTO> toStockDTOS(List<Stock> stocks);

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    List<Stock> toStocks(List<StockDTO> stockDTOS);

}
