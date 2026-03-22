package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.ComplianceRecordDTO;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.cts.housingaid.exception.ComplianceRecordNotFoundException;
import org.cts.housingaid.service.ComplianceRecordService;
import org.cts.housingaid.service.HousingProjectService;
import org.cts.housingaid.service.HousingUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compliance-record")
@AllArgsConstructor
public class ComplianceRecordController {

    private final ComplianceRecordService complianceRecordService;

    private final HousingProjectService housingProjectService;

    private final HousingUnitService housingUnitService;

    @PostMapping("/create-compliance-record")
    public ResponseEntity<String> saveComplianceRecord(
            @Valid @RequestBody ComplianceRecordDTO record) {

        complianceRecordService.saveRecord(record);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Compliance Record created successfully");
    }

    @GetMapping("/get-all-compliance-records")
    public ResponseEntity<List<ComplianceRecordDTO>> getAllRecords()
            throws ComplianceRecordNotFoundException {

        List<ComplianceRecordDTO> list =
                complianceRecordService.getAllRecords();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/search-compliance-record")
    public ResponseEntity<List<ComplianceRecordDTO>>
    getByEntityOrType(

            @RequestParam(required = false) Long entityId,

            @RequestParam(required = false)
            ComplianceEntityType type)

            throws ComplianceRecordNotFoundException {

        List<ComplianceRecordDTO> list =
                complianceRecordService
                        .getRecordsByEntityIdOrType(
                                Math.toIntExact(entityId),
                                type
                        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/get-compliance-dashboard")
    public ResponseEntity<Map<String, Object>>
    getComplianceDashboard() {

        Map<String, Object> dashboard =
                new HashMap<>();

        try {

            dashboard.put(
                    "complianceRecords",
                    complianceRecordService.getAllRecords()
            );

        } catch (Exception e) {

            dashboard.put(
                    "complianceRecords",
                    new ArrayList<>()
            );
        }

        try {

            dashboard.put(
                    "projects",
                    housingProjectService.getAllHousingProjects()
            );

        } catch (Exception e) {

            dashboard.put(
                    "projects",
                    new ArrayList<>()
            );
        }

        try {

            dashboard.put(
                    "housingUnits",
                    housingUnitService.getAllData()
            );

        } catch (Exception e) {

            dashboard.put(
                    "housingUnits",
                    new ArrayList<>()
            );
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dashboard);
    }

}