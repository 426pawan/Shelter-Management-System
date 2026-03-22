package org.cts.housingaid.controller;

import org.cts.housingaid.serviceimpl.ResourcesServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.ResourcesDTO;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.exception.ResourcesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/resources")
public class ResourcesController {

    private final ResourcesServiceImpl resourcesServiceImpl;

    @PostMapping("/create-resources")
    public ResponseEntity<String> createResources(@Valid @RequestBody ResourcesDTO resourcesDTO)
            throws HousingProjectNotFoundException {
        resourcesServiceImpl.createResources(resourcesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Resource created successfully");
    }

    @PutMapping("/update-resources/{id}")
    public ResponseEntity<String> updateResources(@PathVariable("id") Long resourcesId,
                                                  @Valid @RequestBody ResourcesDTO resourcesDTO)
            throws ResourcesNotFoundException, HousingProjectNotFoundException {
        resourcesDTO.setResourcesId(resourcesId);
        resourcesServiceImpl.updateResources(resourcesDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Resource updated successfully");
    }

    @GetMapping("/get-all-resources")
    public ResponseEntity<List<ResourcesDTO>> getAllResources() throws ResourcesNotFoundException {
        List<ResourcesDTO> list = resourcesServiceImpl.getAllData();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/search-resources")
    public ResponseEntity<List<ResourcesDTO>> searchResources(@RequestParam(required = false, defaultValue = "0") Long resourcesId, @RequestParam(required = false) ResourcesType resourcesType, @RequestParam(required = false) ResourcesStatus resourcesStatus) throws ResourcesNotFoundException {

        List<ResourcesDTO> list = resourcesServiceImpl.getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(
                resourcesId, resourcesType, resourcesStatus);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}