package org.cts.housingaid.serviceimpl;

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
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.*;

@Service
@AllArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {
    private final ResourcesRepository resourcesRepository;
    private final HousingProjectRepository housingProjectRepository;
    private final ModelMapper modelMapper;

    public void createResources(ResourcesDTO resourcesDTO) throws HousingProjectNotFoundException {
        HousingProject project = housingProjectRepository.findById(Math.toIntExact(resourcesDTO.getHousingProjectId()))
                .orElseThrow(() -> new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND));

        Resources resources = modelMapper.map(resourcesDTO, Resources.class);
        resources.setHousingProject(project);
        resourcesRepository.save(resources);
    }

    public void updateResources(ResourcesDTO resourcesDTO) throws ResourcesNotFoundException, HousingProjectNotFoundException {
        if (!resourcesRepository.existsById(Math.toIntExact(resourcesDTO.getResourcesId()))) {
            throw new ResourcesNotFoundException(RESOURCE_NOT_FOUND);
        }

        HousingProject project = housingProjectRepository.findById(Math.toIntExact(resourcesDTO.getHousingProjectId()))
                .orElseThrow(() -> new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND));

        Resources resources = modelMapper.map(resourcesDTO, Resources.class);
        resources.setHousingProject(project);
        resourcesRepository.save(resources);
    }

    public List<ResourcesDTO> getSearchedByResourcesIdOrResourcesTypeOrResourcesStatus(Long id, ResourcesType type, ResourcesStatus status) throws ResourcesNotFoundException {
        List<Resources> list = resourcesRepository.findByResourcesIdOrResourcesTypeOrResourcesStatus(id, type, status);
        if (list.isEmpty()) {
            throw new ResourcesNotFoundException(RESOURCE_NOT_FOUND);
        }
        return list.stream()
                .map(res -> modelMapper.map(res, ResourcesDTO.class))
                .toList();
    }

    public List<ResourcesDTO> getAllData() throws ResourcesNotFoundException {
        List<Resources> list = resourcesRepository.findAll();
        if (list.isEmpty()) {
            throw new ResourcesNotFoundException(EMPTY_PROJECT);
        }
        return list.stream()
                .map(res -> modelMapper.map(res, ResourcesDTO.class))
                .toList();
    }
}
