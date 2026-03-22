package org.cts.housingaid.service;

import org.cts.housingaid.dto.ResourcesDTO;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.exception.ResourcesNotFoundException;

import java.util.List;

public interface ResourcesService {

    void updateResources(ResourcesDTO resourcesDTO) throws ResourcesNotFoundException, HousingProjectNotFoundException;

    void createResources(ResourcesDTO resourcesDTO) throws HousingProjectNotFoundException;

    List<ResourcesDTO> getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(Long resourcesId, ResourcesType resourcesType, ResourcesStatus resourcesStatus) throws ResourcesNotFoundException;

    List<ResourcesDTO> getAllResources() throws ResourcesNotFoundException;

}