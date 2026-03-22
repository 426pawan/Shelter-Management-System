package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.EligibilityCheckDTO;
import org.cts.housingaid.service.EligibilityCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eligibility-check")
@AllArgsConstructor
public class EligibilityCheckController {

    private final EligibilityCheckService eligibilityCheckService;

    @GetMapping("/get-all-eligibility-checks")
    public ResponseEntity<List<EligibilityCheckDTO>> getAllEligibilityChecks(){
        List<EligibilityCheckDTO> list = eligibilityCheckService.getAllEligibilityChecks();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-eligibility-check")
    public ResponseEntity<String> createEligibilityCheck(@Valid @RequestBody EligibilityCheckDTO eligibilityCheckDTO){
        eligibilityCheckService.createEligibilityCheck(eligibilityCheckDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Eligibility check created successfully");
    }

    @GetMapping("/search-eligibility-check/{id}")
    public ResponseEntity<EligibilityCheckDTO> getEligibilityCheckById(@PathVariable Long id){
        EligibilityCheckDTO eligibilityCheck = eligibilityCheckService.getEligibilityCheckById(id);
        return ResponseEntity.status(HttpStatus.OK).body(eligibilityCheck);
    }

    @DeleteMapping("/delete-eligibility-check/{id}")
    public ResponseEntity<String> deleteEligibilityCheck(@PathVariable Long id){
        eligibilityCheckService.deleteEligibilityCheck(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eligibility check deleted successfully");
    }

}