package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.HousingProjectRepository;
import org.cts.housingaid.dao.ResourcesRepository;
import org.cts.housingaid.dto.ResourcesDTO;
import org.cts.housingaid.entity.HousingProject;
import org.cts.housingaid.entity.Resources;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.exception.ResourcesNotFoundException;
import org.cts.housingaid.service.ResourcesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_PROJECT_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.RESOURCE_NOT_FOUND;

@Service
@AllArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final HousingProjectRepository housingProjectRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateResources(ResourcesDTO resourcesDTO) throws ResourcesNotFoundException, HousingProjectNotFoundException {
        Optional<Resources> optionalResources = resourcesRepository.findById(resourcesDTO.getResourcesId());
        if(optionalResources.isEmpty()){
            throw new ResourcesNotFoundException(RESOURCE_NOT_FOUND);
        }
        HousingProject housingProject = housingProjectRepository.findById(resourcesDTO.getHousingProjectId()).orElseThrow(() -> new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND));
        Resources existingResources = optionalResources.get();
        modelMapper.map(resourcesDTO, existingResources);
        existingResources.setHousingProject(housingProject);
        resourcesRepository.save(existingResources);
    }

    @Override
    public void createResources(ResourcesDTO resourcesDTO) throws HousingProjectNotFoundException {
        HousingProject housingProject = housingProjectRepository.findById(resourcesDTO.getHousingProjectId()).orElseThrow(() -> new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND));
        Resources resources = modelMapper.map(resourcesDTO, Resources.class);
        resources.setHousingProject(housingProject);
        resourcesRepository.save(resources);
    }

    @Override
    public List<ResourcesDTO> getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(Long resourcesId, ResourcesType resourcesType, ResourcesStatus resourcesStatus) throws ResourcesNotFoundException {
        List<Resources> resources = resourcesRepository.findByResourcesIdOrResourcesTypeOrResourcesStatus(resourcesId, resourcesType, resourcesStatus);
        if(resources.isEmpty()){
            throw new ResourcesNotFoundException(EMPTY_DATA);
        }
        return resources.stream().map(res -> modelMapper.map(res, ResourcesDTO.class)).toList();
    }

    @Override
    public List<ResourcesDTO> getAllResources() throws ResourcesNotFoundException {
        List<Resources> resources = resourcesRepository.findAll();
        if(resources.isEmpty()){
            throw new ResourcesNotFoundException(EMPTY_DATA);
        }
        return resources.stream().map(res -> modelMapper.map(res, ResourcesDTO.class)).toList();
    }

}