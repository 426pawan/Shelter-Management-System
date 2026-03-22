package org.cts.housingaid.service;

import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.exception.HousingUnitNotFoundException;

import java.util.List;

public interface HousingUnitService {

    void updateHousingUnit(HousingUnitDTO housingUnitDTO) throws HousingUnitNotFoundException;

    void createHousingUnit(HousingUnitDTO housingUnitDTO);

    List<HousingUnitDTO> getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(Long housingUnitId, String housingUnitLocation, HousingUnitType housingUnitType) throws HousingUnitNotFoundException;

    List<HousingUnitDTO> getAllHousingUnits() throws HousingUnitNotFoundException;

    Object getAllData();
}