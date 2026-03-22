package org.cts.housingaid.controller;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.AllocationRequestDTO;
import org.cts.housingaid.dto.AllocationResponseDTO;
import org.cts.housingaid.service.AllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocation")
@AllArgsConstructor
public class AllocationController {

    private final AllocationService allocationService;

    @PostMapping("/create-allocation")
    public ResponseEntity<AllocationResponseDTO> createAllocation(@RequestBody AllocationRequestDTO allocationRequestDTO){
        AllocationResponseDTO response = allocationService.createAllocation(allocationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}