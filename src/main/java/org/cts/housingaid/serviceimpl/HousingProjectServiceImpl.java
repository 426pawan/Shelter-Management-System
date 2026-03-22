package org.cts.housingaid.serviceimpl;

import org.cts.housingaid.service.HousingProjectService;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.HousingProjectRepository;
import org.cts.housingaid.dto.HousingProjectDTO;
import org.cts.housingaid.entity.HousingProject;
import org.cts.housingaid.exception.HousingProjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_PROJECT;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_PROJECT_NOT_FOUND;

@Service
@AllArgsConstructor
public class HousingProjectServiceImpl implements HousingProjectService {

    private final HousingProjectRepository housingProjectRepository;
    private final ModelMapper modelMapper;


    public void updateHousingProject(HousingProjectDTO housingProjectDTO) throws HousingProjectNotFoundException {
        HousingProject housingProject=modelMapper.map(housingProjectDTO, HousingProject.class);
        Optional<HousingProject> optionalHousingProject=housingProjectRepository.findById(Math.toIntExact(housingProject.getHousingProjectId()));
        if(optionalHousingProject.isPresent()){
            housingProjectRepository.save(housingProject);
        }else{
            throw new HousingProjectNotFoundException(HOUSING_PROJECT_NOT_FOUND);
        }
    }

    public void createHousingProject(HousingProjectDTO housingProjectDTO) {
        HousingProject housingProject=modelMapper.map(housingProjectDTO, HousingProject.class);
        housingProjectRepository.save(housingProject);
    }

    public List<HousingProjectDTO> getSearchedByHousingProjectIdORHousingProjectTitle(Long housingProjectId, String housingProjectTitle) throws HousingProjectNotFoundException {
        List<HousingProject> list= housingProjectRepository.findByHousingProjectIdOrHousingProjectTitle(housingProjectId,housingProjectTitle);
        if(list.isEmpty()){
            throw new HousingProjectNotFoundException(EMPTY_PROJECT);
        }
        return list
                .stream()
                .map(housingProject -> modelMapper.map(housingProject,HousingProjectDTO.class))
                .toList();
    }

    public List<HousingProjectDTO> getAllHousingProjects() throws HousingProjectNotFoundException {
        List<HousingProject> housingProjects=housingProjectRepository.findAll();
        if(housingProjects.isEmpty()){
            throw new HousingProjectNotFoundException(EMPTY_PROJECT);
        }
        return housingProjects
                .stream()
                .map(housingProject -> modelMapper.map(housingProject,HousingProjectDTO.class))
                .toList();
    }
}
