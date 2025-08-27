package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.model.Result;

@Mapper
public interface ResultMapper {
    public void insertResult(Result result);
}
