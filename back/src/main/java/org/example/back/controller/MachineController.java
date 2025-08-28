package org.example.back.controller;

import org.example.back.model.Machine;
import org.example.back.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("")
    public List<Machine> findAll() { return machineService.findAll();}
}
