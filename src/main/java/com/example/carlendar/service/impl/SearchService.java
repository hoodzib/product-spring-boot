package com.example.carlendar.service.impl;

import com.example.carlendar.entity.ProductEntity;
import com.example.carlendar.entity.RecordEntity;
import com.example.carlendar.repository.ProductRepository;
import com.example.carlendar.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecordRepository recordRepository;

    public List<ProductEntity> getAllPD(){
        List<ProductEntity> list =  (List<ProductEntity>)productRepository.findAll();
        return list;
    }

    public List<ProductEntity> getByKeywordPD(String keyword){
        return productRepository.findByKeyword(keyword);
    }

    public List<RecordEntity> getAllRC(){
        List<RecordEntity> list =  (List<RecordEntity>)recordRepository.findAll();
        return list;
    }

    public List<RecordEntity> getByKeywordRC(String keyword){
        return recordRepository.findByKeyword(keyword);
    }
}
