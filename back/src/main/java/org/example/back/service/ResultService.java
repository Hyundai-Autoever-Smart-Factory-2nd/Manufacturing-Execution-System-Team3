package org.example.back.service;

import org.example.back.mapper.ResultMapper;
import org.example.back.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    @Autowired
    ResultMapper resultMapper;

    public void insertResult(Result result) {
        resultMapper.insertResult(result);
    }
}
