package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<Product> findAll();
}
