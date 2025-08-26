package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.model.Machine;

import java.util.List;

@Mapper
public interface MachineMapper {
    public List<Machine> findAll();
}
