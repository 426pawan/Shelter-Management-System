package org.cts.housingaid.controller;

import lombok.AllArgsConstructor;
import org.cts.housingaid.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/dashboard-stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {

        Map<String, Object> stats =
                reportService.getDashboardStats();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stats);
    }

}