package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.AuditDTO;
import org.cts.housingaid.exception.AuditNotFoundException;
import org.cts.housingaid.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
@AllArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @PostMapping("/create-audit")
    public ResponseEntity<String> createAudit(
            @Valid @RequestBody AuditDTO auditDTO) {

        auditService.createAudit(auditDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Audit created successfully");
    }

    @PutMapping("/update-audit")
    public ResponseEntity<String> updateAudit(
            @Valid @RequestBody AuditDTO auditDTO)
            throws AuditNotFoundException {

        auditService.updateAudit(auditDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Audit updated successfully");
    }

    @GetMapping("/get-all-audits")
    public ResponseEntity<List<AuditDTO>> getAllAudits()
            throws AuditNotFoundException {

        List<AuditDTO> list =
                auditService.getAllAudits();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/search-audit")
    public ResponseEntity<List<AuditDTO>> searchAudit(

            @RequestParam(required = false)
            Long officerId,

            @RequestParam(required = false)
            String scope)

            throws AuditNotFoundException {

        List<AuditDTO> list =
                auditService
                        .getAuditsByOfficerOrScope(
                                officerId,
                                scope
                        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

}