package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.model.Worker;

import java.util.List;

@Mapper
public interface WorkerMapper {
    public List<Worker> findAll();
}
