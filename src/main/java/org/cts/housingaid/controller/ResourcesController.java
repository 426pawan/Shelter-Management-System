package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.ResourcesDTO;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.exception.ResourcesNotFoundException;
import org.cts.housingaid.service.ResourcesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;

    @GetMapping("/get-all-resources")
    public ResponseEntity<List<ResourcesDTO>> getAllResources(){
        List<ResourcesDTO> list = resourcesService.getAllResources();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-resources")
    public ResponseEntity<String> createResources(@Valid @RequestBody ResourcesDTO resourcesDTO) throws HousingProjectNotFoundException{
        resourcesService.createResources(resourcesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Resource created successfully");
    }

    @PutMapping("/update-resources/{id}")
    public ResponseEntity<String> updateResources(@PathVariable Long id, @Valid @RequestBody ResourcesDTO resourcesDTO) throws ResourcesNotFoundException, HousingProjectNotFoundException{
        resourcesDTO.setResourcesId(id);
        resourcesService.updateResources(resourcesDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Resource updated successfully");
    }

    @GetMapping("/search-resources")
    public ResponseEntity<List<ResourcesDTO>> searchResources(@RequestParam(required = false) Long resourcesId, @RequestParam(required = false) ResourcesType resourcesType, @RequestParam(required = false) ResourcesStatus resourcesStatus){
        List<ResourcesDTO> list = resourcesService.getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(resourcesId, resourcesType, resourcesStatus);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}