package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.HousingProjectDTO;
import org.cts.housingaid.service.HousingProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/housing-project")
@AllArgsConstructor
public class HousingProjectController {

    private final HousingProjectService housingProjectService;

    @GetMapping("/get-all-housing-projects")
    public ResponseEntity<List<HousingProjectDTO>> getAllHousingProjects(){
        List<HousingProjectDTO> list = housingProjectService.getAllHousingProjects();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-housing-project")
    public ResponseEntity<String> createHousingProject(@Valid @RequestBody HousingProjectDTO housingProjectDTO){
        housingProjectService.createHousingProject(housingProjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Housing Project created successfully");
    }

    @PutMapping("/update-housing-project/{id}")
    public ResponseEntity<String> updateHousingProject(@PathVariable Long id, @Valid @RequestBody HousingProjectDTO housingProjectDTO){
        housingProjectDTO.setHousingProjectId(id);
        housingProjectService.updateHousingProject(housingProjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Housing Project updated successfully");
    }

    @GetMapping("/search-housing-project")
    public ResponseEntity<List<HousingProjectDTO>> searchHousingProject(@RequestParam(required = false) Long housingProjectId, @RequestParam(required = false) String housingProjectTitle){
        List<HousingProjectDTO> list = housingProjectService.getSearchedByHousingProjectIdORHousingProjectTitle(housingProjectId, housingProjectTitle);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}