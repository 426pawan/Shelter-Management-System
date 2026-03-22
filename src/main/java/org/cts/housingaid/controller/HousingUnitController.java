package org.cts.housingaid.controller;

import org.cts.housingaid.serviceimpl.HousingUnitServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.exception.HousingUnitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/housing-unit")
public class HousingUnitController {

    private final HousingUnitServiceImpl housingUnitServiceImpl;

    @PostMapping("/create-housing-unit")
    public ResponseEntity<String> createHousingUnit(@Valid @RequestBody HousingUnitDTO housingUnitDTO) {
        housingUnitServiceImpl.createHousingUnit(housingUnitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Housing Unit created successfully");
    }

    @PutMapping("/update-housing-unit/{id}")
    public ResponseEntity<String> updateHousingUnit(@PathVariable("id") Long housingUnitId, @Valid @RequestBody HousingUnitDTO housingUnitDTO) throws HousingUnitNotFoundException {
        housingUnitDTO.setHousingUnitId(housingUnitId);
        housingUnitServiceImpl.updateHousingUnit(housingUnitDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Housing Unit updated successfully");
    }

    @GetMapping("/get-all-housing-units")
    public ResponseEntity<List<HousingUnitDTO>> getAllHousingUnits() throws HousingUnitNotFoundException {
        List<HousingUnitDTO> list = housingUnitServiceImpl.getAllData();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/search-housing-unit")
    public ResponseEntity<List<HousingUnitDTO>> searchHousingUnit(
            @RequestParam(required = false, defaultValue = "0") Long housingUnitId,
            @RequestParam(required = false) String housingUnitLocation,
            @RequestParam(required = false) HousingUnitType housingUnitType) throws HousingUnitNotFoundException {

        List<HousingUnitDTO> list = housingUnitServiceImpl.getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(
                housingUnitId, housingUnitLocation, housingUnitType);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}