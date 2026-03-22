package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.CitizenDTO;
import org.cts.housingaid.dto.CitizenRegistrationDTO;
import org.cts.housingaid.service.CitizenService;
import org.cts.housingaid.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen")
@AllArgsConstructor
public class CitizenController {

    private final CitizenService citizenService;
    private final RegistrationService registrationService;

    @GetMapping("/get-all-citizens")
    public ResponseEntity<List<CitizenDTO>> getAllCitizens(){
        List<CitizenDTO> list = citizenService.getAllCitizens();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/register-citizen")
    public ResponseEntity<CitizenRegistrationDTO> registerCitizen(@Valid @RequestBody CitizenRegistrationDTO dto){
        CitizenRegistrationDTO result = registrationService.registerCitizen(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/search-citizen/{id}")
    public ResponseEntity<CitizenDTO> getCitizenById(@PathVariable Long id){
        CitizenDTO citizen = citizenService.getCitizenById(id);
        return ResponseEntity.status(HttpStatus.OK).body(citizen);
    }

    @PutMapping("/update-citizen/{id}")
    public ResponseEntity<String> updateCitizen(@PathVariable Long id, @Valid @RequestBody CitizenDTO citizenDTO){
        citizenDTO.setCitizenId(id);
        citizenService.updateCitizen(citizenDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Citizen updated successfully");
    }

    @DeleteMapping("/delete-citizen/{id}")
    public ResponseEntity<String> deleteCitizen(@PathVariable Long id){
        citizenService.deleteCitizen(id);
        return ResponseEntity.status(HttpStatus.OK).body("Citizen deleted successfully");
    }

}