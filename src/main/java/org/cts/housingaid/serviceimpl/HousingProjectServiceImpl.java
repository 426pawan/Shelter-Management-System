package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.HousingProjectRepository;
import org.cts.housingaid.dto.HousingProjectDTO;
import org.cts.housingaid.entity.HousingProject;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.cts.housingaid.service.HousingProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_PROJECT_NOT_FOUND;

@Service
@AllArgsConstructor
public class HousingProjectServiceImpl implements HousingProjectService {

    private final HousingProjectRepository housingProjectRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateHousingProject(HousingProjectDTO housingProjectDTO) throws HousingProjectNotFoundException {
        Optional<HousingProject> optionalHousingProject = housingProjectRepository.findById(housingProjectDTO.getHousingProjectId());
        if(optionalHousingProject.isEmpty()){
            throw new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND);
        }
        HousingProject existingProject = optionalHousingProject.get();
        modelMapper.map(housingProjectDTO, existingProject);
        housingProjectRepository.save(existingProject);
    }

    @Override
    public void createHousingProject(HousingProjectDTO housingProjectDTO) {
        HousingProject housingProject = modelMapper.map(housingProjectDTO, HousingProject.class);
        housingProjectRepository.save(housingProject);
    }

    @Override
    public List<HousingProjectDTO> getSearchedByHousingProjectIdORHousingProjectTitle(Long housingProjectId, String housingProjectTitle) throws HousingProjectNotFoundException {
        List<HousingProject> housingProjects = housingProjectRepository.findByHousingProjectIdOrHousingProjectTitle(housingProjectId, housingProjectTitle);
        if(housingProjects.isEmpty()){
            throw new HousingProjectNotFoundException(EMPTY_DATA);
        }
        return housingProjects.stream().map(project -> modelMapper.map(project, HousingProjectDTO.class)).toList();
    }

    @Override
    public List<HousingProjectDTO> getAllHousingProjects() throws HousingProjectNotFoundException {
        List<HousingProject> housingProjects = housingProjectRepository.findAll();
        if(housingProjects.isEmpty()){
            throw new HousingProjectNotFoundException(EMPTY_DATA);
        }

        return housingProjects.stream().map(project -> modelMapper.map(project, HousingProjectDTO.class)).toList();
    }

}