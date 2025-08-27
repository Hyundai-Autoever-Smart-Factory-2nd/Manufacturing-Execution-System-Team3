package org.example.back.controller;

import org.example.back.model.Result;
import org.example.back.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/result")
public class ResultController {
    @Autowired
    ResultService resultService;

    @PostMapping("")
    public void insertResult(
            @RequestBody
            Result result
    ) {
        resultService.insertResult(result);
    }
}
