package com.github.paulocesar.bootcamp.mapper;


import com.github.paulocesar.bootcamp.model.Stock;
import com.github.paulocesar.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());

        return stock;
    }

    public StockDTO toDTo(Stock stock) {

        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());

        return  dto;

    }

    public List<StockDTO> toDTo(List<Stock> listStock) {
        return listStock.stream().map(this::toDTo).collect(Collectors.toList());

    }
}
