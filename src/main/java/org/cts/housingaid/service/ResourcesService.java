package org.cts.housingaid.service;

import org.cts.housingaid.dto.ResourcesDTO;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.exception.ResourcesNotFoundException;

import java.util.List;

public interface ResourcesService {
    void createResources(ResourcesDTO resourcesDTO)
            throws HousingProjectNotFoundException;

    void updateResources(ResourcesDTO resourcesDTO)
            throws ResourcesNotFoundException, HousingProjectNotFoundException;

    List<ResourcesDTO> getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(
            Long id, ResourcesType type, ResourcesStatus status)
            throws ResourcesNotFoundException;

    List<ResourcesDTO> getAllData() throws ResourcesNotFoundException;
}
