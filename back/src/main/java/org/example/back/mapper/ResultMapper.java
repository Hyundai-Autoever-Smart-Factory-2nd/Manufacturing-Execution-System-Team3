package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.model.Result;

import java.util.List;

@Mapper
public interface ResultMapper {
    public void insertResult(Result result);
    public Boolean existsByWorksheetId(Long id);
    public List<Result> findByWorksheetId(Long id);
}
