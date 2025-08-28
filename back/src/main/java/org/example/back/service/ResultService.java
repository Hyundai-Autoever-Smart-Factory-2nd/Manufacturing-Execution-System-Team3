package org.example.back.service;

import org.example.back.dto.PostResultRequest;
import org.example.back.mapper.ResultMapper;
import org.example.back.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    @Autowired
    ResultMapper resultMapper;

    public void insertResult(PostResultRequest request) {
        Result result = new Result();
        result.setWorksheetId(request.getWorksheetId());
        result.setPassed(request.getPassed());
        resultMapper.insertResult(result);
    }
    public java.util.List<Result> findByWorksheetId(Long id) {
        return resultMapper.findByWorksheetId(id);
    }
}
