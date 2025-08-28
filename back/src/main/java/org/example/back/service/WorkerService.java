package org.example.back.service;

import org.example.back.mapper.WorkerMapper;
import org.example.back.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    WorkerMapper workerMapper;

    public List<Worker> findAll() { return workerMapper.findAll(); }
}
