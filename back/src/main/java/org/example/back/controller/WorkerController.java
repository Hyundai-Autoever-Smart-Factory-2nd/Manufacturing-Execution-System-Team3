package org.example.back.controller;

import org.example.back.model.Worker;
import org.example.back.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("")
    public List<Worker> findAll() { return workerService.findAll(); }
}
