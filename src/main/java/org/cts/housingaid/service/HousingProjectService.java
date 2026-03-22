package org.cts.housingaid.service;

import org.cts.housingaid.dto.HousingProjectDTO;
import org.cts.housingaid.exception.HousingProjectNotFoundException;

import java.util.List;

public interface HousingProjectService {

    void updateHousingProject(HousingProjectDTO housingProjectDTO) throws HousingProjectNotFoundException;

    void createHousingProject(HousingProjectDTO housingProjectDTO);

    List<HousingProjectDTO> getSearchedByHousingProjectIdORHousingProjectTitle(Long housingProjectId, String housingProjectTitle) throws HousingProjectNotFoundException;

    List<HousingProjectDTO> getAllHousingProjects() throws HousingProjectNotFoundException;
}