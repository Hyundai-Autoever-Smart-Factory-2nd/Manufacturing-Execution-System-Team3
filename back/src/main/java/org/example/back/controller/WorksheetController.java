package org.example.back.controller;

import org.example.back.dto.WorksheetWithAllResponse;
import org.example.back.model.Worksheet;
import org.example.back.service.WorksheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worksheet")
public class WorksheetController {
    @Autowired
    private WorksheetService worksheetService;

    @GetMapping("")
    public List<WorksheetWithAllResponse> getAllWorksheets() { return worksheetService.findAll(); }

    @PostMapping("")
    public void createWorksheet(
            @RequestBody
            Worksheet worksheet) {
        worksheetService.insertWorksheet(worksheet);
    }

    @PutMapping("/{id}")
    public void updateWorksheet(
            @PathVariable Long id,
            @RequestBody
            Worksheet worksheet) {
        worksheet.setWorksheetId(id);
        worksheetService.updateWorksheet(worksheet);
    }

    @GetMapping("/{id}")
    public WorksheetWithAllResponse getWorksheetById(
            @PathVariable Long id) {
        return worksheetService.findByMachineId(id);
    }
}
