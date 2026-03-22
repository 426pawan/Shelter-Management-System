package org.cts.housingaid.controller;


import org.cts.housingaid.serviceimpl.HousingProjectServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.HousingProjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/housing-project")
@AllArgsConstructor
public class HousingProjectController {

    private final HousingProjectServiceImpl housingProjectServiceImpl;

    @RequestMapping(value = "/get-all-housing-projects", method = RequestMethod.GET)
    public ResponseEntity<List<HousingProjectDTO>> getAllHousingProjects() {
        List<HousingProjectDTO> list=housingProjectServiceImpl.getAllHousingProjects();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-housing-project")
    public ResponseEntity<String> createHousingProject(@Valid @RequestBody HousingProjectDTO housingProjectDTO) {
        housingProjectServiceImpl.createHousingProject(housingProjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Housing Project created successfully");
    }

    @PutMapping("/update-housing-project/{id}")
    public ResponseEntity<String> updateHousingProject(@PathVariable("id") Long housingProjectId, @Valid @RequestBody HousingProjectDTO housingProjectDTO) {
        housingProjectDTO.setHousingProjectId(housingProjectId);
        housingProjectServiceImpl.updateHousingProject(housingProjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Housing Project updated successfully");
    }

    @GetMapping("/search-housing-project")
    public ResponseEntity<List<HousingProjectDTO>> searchHousingProject(@RequestParam(required = false) Long housingProjectId, @RequestParam(required = false) String housingProjectTitle) {
        List<HousingProjectDTO> list=housingProjectServiceImpl.getSearchedByHousingProjectIdORHousingProjectTitle(housingProjectId,housingProjectTitle);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
