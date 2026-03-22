package org.cts.housingaid.service;

import org.cts.housingaid.dto.HousingProjectDTO;

import java.util.List;


public interface HousingProjectService {


    public void updateHousingProject(HousingProjectDTO housingProjectDTO);

    public void createHousingProject(HousingProjectDTO housingProjectDTO);

    public List<HousingProjectDTO> getSearchedByHousingProjectIdORHousingProjectTitle(Long housingProjectId, String housingProjectTitle);

    public List<HousingProjectDTO> getAllHousingProjects();
}
