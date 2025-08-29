package org.example.back.controller;

import org.example.back.dto.PostResultRequest;
import org.example.back.model.Result;
import org.example.back.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
public class ResultController {
    @Autowired
    ResultService resultService;

    @GetMapping("/{id}")
    public java.util.List<Result> getResultsByWorksheetId(
            @PathVariable("id") Long id
    ) {
        return resultService.findByWorksheetId(id);
    }

    @PostMapping("")
    public void insertResult(
            @RequestBody
            PostResultRequest request
    ) {
        resultService.insertResult(request);
    }
}
