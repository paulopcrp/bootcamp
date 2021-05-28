package com.github.paulocesar.bootcamp.service;

import com.github.paulocesar.bootcamp.exceptions.BusinessException;
import com.github.paulocesar.bootcamp.exceptions.NotFoundException;
import com.github.paulocesar.bootcamp.mapper.StockMapper;
import com.github.paulocesar.bootcamp.model.Stock;
import com.github.paulocesar.bootcamp.model.dto.StockDTO;
import com.github.paulocesar.bootcamp.repository.StockRepository;
import com.github.paulocesar.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {


    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);

        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTo(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);

        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTo(stock);

    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;

    }

    @Transactional(readOnly = true)
    public List<StockDTO> findALL() {

        return mapper.toDTo(repository.findAll());
    }

    @Transactional(readOnly = true)
    public StockDTO findById(long id) {
        return repository.findById(id).map(mapper::toDTo).orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDTo).orElseThrow(NotFoundException::new);

    }


}
