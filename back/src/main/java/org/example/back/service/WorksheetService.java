package org.example.back.service;

import org.example.back.dto.CreateWorksheetRequest;
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
    public WorksheetWithAllResponse findByMachineId(Long id) {
        WorksheetWithAllResponse response = worksheetMapper.findByMachineId(id);
        if (response == null) {
            throw new IllegalArgumentException("해당 설비의 금일 작업지시서가 존재하지 않습니다.");
        }
        return response;
    }

    public void insertWorksheet(CreateWorksheetRequest request) {
        if (worksheetMapper.existsByWorkDateAndMachineId(request)) {
            throw new IllegalArgumentException(request.getWorkDate() + " 에 해당 장비는 이미 작업지시서가 존재합니다.");
        }
        Worksheet worksheet = new Worksheet();
        worksheet.setAmount(request.getAmount());
        worksheet.setWorkDate(request.getWorkDate());
        worksheet.setMachineId(request.getMachineId());
        worksheet.setProductId(request.getProductId());
        worksheet.setWorkerId(request.getWorkerId());
        worksheetMapper.insertWorksheet(worksheet);
    }
    public void updateWorksheet(Worksheet worksheet) { worksheetMapper.updateWorksheet(worksheet); }
    public void deleteWorksheet(Long id) { worksheetMapper.deleteWorksheet(id); }
}
