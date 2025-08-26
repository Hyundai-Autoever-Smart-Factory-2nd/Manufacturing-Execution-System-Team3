package org.example.back.service;

import org.example.back.mapper.MachineMapper;
import org.example.back.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineMapper machineMapper;

    public List<Machine> findAll() { return machineMapper.findAll(); }
}
