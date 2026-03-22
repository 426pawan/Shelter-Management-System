package org.cts.housingaid.service;

import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.exception.HousingUnitNotFoundException;

import java.util.List;

public interface HousingUnitService {
    void createHousingUnit(HousingUnitDTO housingUnitDTO);

    void updateHousingUnit(HousingUnitDTO housingUnitDTO) throws HousingUnitNotFoundException;

    List<HousingUnitDTO> getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(
            Long id, String location, HousingUnitType type) throws HousingUnitNotFoundException;

    List<HousingUnitDTO> getAllData() throws HousingUnitNotFoundException;
}
