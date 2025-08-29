package org.example.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.back.dto.CreateWorksheetRequest;
import org.example.back.dto.WorksheetWithAllResponse;
import org.example.back.model.Worksheet;

import java.util.List;

@Mapper
public interface WorksheetMapper {
    public List<WorksheetWithAllResponse> findAll();
    public WorksheetWithAllResponse findByMachineId(Long id);
    public void insertWorksheet(Worksheet worksheet);
    public void updateWorksheet(Worksheet worksheet);
    public void deleteWorksheet(Long id);
    public boolean existsByWorkDateAndMachineId(CreateWorksheetRequest createWorksheetRequest);
}
