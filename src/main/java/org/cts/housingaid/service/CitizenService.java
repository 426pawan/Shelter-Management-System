package org.cts.housingaid.service;

import org.cts.housingaid.dto.CitizenDTO;
import org.cts.housingaid.exception.CitizenNotFoundException;

import java.util.List;

public interface CitizenService {

    void updateCitizen(CitizenDTO citizenDTO) throws CitizenNotFoundException;

    CitizenDTO getCitizenById(Long citizenId) throws CitizenNotFoundException;

    void deleteCitizen(Long citizenId) throws CitizenNotFoundException;

    List<CitizenDTO> getAllCitizens() throws CitizenNotFoundException;

}