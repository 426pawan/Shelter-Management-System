package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.service.HousingUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/housing-unit")
@AllArgsConstructor
public class HousingUnitController {

    private final HousingUnitService housingUnitService;

    @GetMapping("/get-all-housing-units")
    public ResponseEntity<List<HousingUnitDTO>> getAllHousingUnits(){
        List<HousingUnitDTO> list = housingUnitService.getAllHousingUnits();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-housing-unit")
    public ResponseEntity<String> createHousingUnit(@Valid @RequestBody HousingUnitDTO housingUnitDTO){
        housingUnitService.createHousingUnit(housingUnitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Housing Unit created successfully");
    }

    @PutMapping("/update-housing-unit/{id}")
    public ResponseEntity<String> updateHousingUnit(@PathVariable Long id, @Valid @RequestBody HousingUnitDTO housingUnitDTO){
        housingUnitDTO.setHousingUnitId(id);
        housingUnitService.updateHousingUnit(housingUnitDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Housing Unit updated successfully");
    }

    @GetMapping("/search-housing-unit")
    public ResponseEntity<List<HousingUnitDTO>> searchHousingUnit(@RequestParam(required = false) Long housingUnitId, @RequestParam(required = false) String housingUnitLocation, @RequestParam(required = false) HousingUnitType housingUnitType){
        List<HousingUnitDTO> list = housingUnitService.getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(housingUnitId, housingUnitLocation, housingUnitType);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}