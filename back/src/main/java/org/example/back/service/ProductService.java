package org.example.back.service;

import org.example.back.mapper.ProductMapper;
import org.example.back.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAll() { return productMapper.findAll(); }
}
