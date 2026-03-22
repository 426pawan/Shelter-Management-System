package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.HousingApplicationDTO;
import org.cts.housingaid.enums.HousingApplicationStatus;
import org.cts.housingaid.service.HousingApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/housing-application")
@AllArgsConstructor
public class HousingApplicationController {

    private final HousingApplicationService housingApplicationService;

    @GetMapping("/get-all-housing-applications")
    public ResponseEntity<List<HousingApplicationDTO>> getAllHousingApplications(){
        List<HousingApplicationDTO> list = housingApplicationService.getAllHousingApplications();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-housing-application")
    public ResponseEntity<String> createHousingApplication(@Valid @RequestBody HousingApplicationDTO housingApplicationDTO){
        housingApplicationService.createHousingApplication(housingApplicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Housing application created successfully");
    }

    @GetMapping("/search-housing-application/{id}")
    public ResponseEntity<HousingApplicationDTO> getHousingApplicationById(@PathVariable Long id){
        HousingApplicationDTO housingApplication = housingApplicationService.getHousingApplicationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(housingApplication);
    }

    @GetMapping("/search-housing-application-by-status")
    public ResponseEntity<List<HousingApplicationDTO>> getApplicationsByStatus(@RequestParam HousingApplicationStatus housingApplicationStatus){
        List<HousingApplicationDTO> list = housingApplicationService.getApplicationsByStatus(housingApplicationStatus);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/delete-housing-application/{id}")
    public ResponseEntity<String> deleteHousingApplication(@PathVariable Long id){
        housingApplicationService.deleteHousingApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body("Housing application deleted successfully");
    }

}