package org.cts.housingaid.service;

import org.cts.housingaid.dto.HousingApplicationDTO;
import org.cts.housingaid.enums.HousingApplicationStatus;
import org.cts.housingaid.exception.HousingApplicationNotFoundException;

import java.util.List;

public interface HousingApplicationService {

    void createHousingApplication(HousingApplicationDTO housingApplicationDTO);

    HousingApplicationDTO getHousingApplicationById(Long housingApplicationId) throws HousingApplicationNotFoundException;

    List<HousingApplicationDTO> getApplicationsByStatus(HousingApplicationStatus housingApplicationStatus) throws HousingApplicationNotFoundException;

    List<HousingApplicationDTO> getAllHousingApplications() throws HousingApplicationNotFoundException;

    void deleteHousingApplication(Long housingApplicationId) throws HousingApplicationNotFoundException;

}