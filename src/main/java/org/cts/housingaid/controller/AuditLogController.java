package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.AuditLogDTO;
import org.cts.housingaid.exception.AuditLogNotFoundException;
import org.cts.housingaid.exception.UsersNotFoundException;
import org.cts.housingaid.service.AuditLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-log")
@AllArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping("/get-all-audit-logs")
    public ResponseEntity<List<AuditLogDTO>> getAllAuditLogs(){
        List<AuditLogDTO> list = auditLogService.getAllAuditLogs();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-audit-log")
    public ResponseEntity<String> createAuditLog(@Valid @RequestBody AuditLogDTO auditLogDTO) throws UsersNotFoundException{
        auditLogService.createAuditLog(auditLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Audit log created successfully");
    }

    @DeleteMapping("/delete-audit-log/{id}")
    public ResponseEntity<String> deleteAuditLog(@PathVariable Long id) throws AuditLogNotFoundException{
        auditLogService.deleteAuditLog(id);
        return ResponseEntity.status(HttpStatus.OK).body("Audit log deleted successfully");
    }

    @GetMapping("/search-audit-log/{id}")
    public ResponseEntity<AuditLogDTO> getAuditLogById(@PathVariable Long id) throws AuditLogNotFoundException{
        AuditLogDTO auditLog = auditLogService.getAuditLogById(id);
        return ResponseEntity.status(HttpStatus.OK).body(auditLog);
    }

    @GetMapping("/search-audit-log-by-user/{userId}")
    public ResponseEntity<List<AuditLogDTO>> getLogsByUserId(@PathVariable Long userId){
        List<AuditLogDTO> list = auditLogService.getLogsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}