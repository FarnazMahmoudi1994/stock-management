package com.example.stock_manager.stock;

import com.example.stock_manager.product.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StockMapper {

    @Mappings({@Mapping(source = "productId", target = "product.id")})
    Stock toStock(StockDTO stockDTO);

    @Mappings({@Mapping(source = "product.id", target = "productId")})
    StockDTO toStockDTO(Stock stock);

}
