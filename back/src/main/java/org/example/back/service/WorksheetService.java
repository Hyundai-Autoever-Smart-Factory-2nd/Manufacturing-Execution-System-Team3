package org.example.back.service;

import org.example.back.dto.WorksheetWithAllResponse;
import org.example.back.mapper.WorksheetMapper;
import org.example.back.model.Worksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksheetService {

    @Autowired
    WorksheetMapper worksheetMapper;

    public List<WorksheetWithAllResponse> findAll() { return worksheetMapper.findAll(); }
    public WorksheetWithAllResponse findByMachineId(Long id) { return worksheetMapper.findByMachineId(id); }
    public void insertWorksheet(Worksheet worksheet) { worksheetMapper.insertWorksheet(worksheet); }
    public void updateWorksheet(Worksheet worksheet) { worksheetMapper.updateWorksheet(worksheet); }
    public void deleteWorksheet(Long id) { worksheetMapper.deleteWorksheet(id); }
}
