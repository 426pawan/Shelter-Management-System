package org.cts.housingaid.serviceimpl;

import org.cts.housingaid.dao.HousingUnitRepository;
import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.entity.HousingUnit;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.exception.HousingUnitNotFoundException;
import org.cts.housingaid.service.HousingUnitService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_PROJECT;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_UNIT_NOT_FOUND;

@Service
@AllArgsConstructor
public class HousingUnitServiceImpl implements HousingUnitService {

    private final HousingUnitRepository housingUnitRepository;
    private final ModelMapper modelMapper;

    public void createHousingUnit(HousingUnitDTO housingUnitDTO) {
        HousingUnit housingUnit = modelMapper.map(housingUnitDTO, HousingUnit.class);
        housingUnitRepository.save(housingUnit);
    }

    public void updateHousingUnit(HousingUnitDTO housingUnitDTO) throws HousingUnitNotFoundException {
        if (!housingUnitRepository.existsById(Math.toIntExact(housingUnitDTO.getHousingUnitId()))) {
            throw new HousingUnitNotFoundException(HOUSING_UNIT_NOT_FOUND);
        }
        HousingUnit housingUnit = modelMapper.map(housingUnitDTO, HousingUnit.class);
        housingUnitRepository.save(housingUnit);
    }

    public List<HousingUnitDTO> getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(Long id, String location, HousingUnitType type) throws HousingUnitNotFoundException {
        List<HousingUnit> list = housingUnitRepository.findByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(id, location, type);
        if (list.isEmpty()) {
            throw new HousingUnitNotFoundException(EMPTY_PROJECT);
        }
        return list.stream()
                .map(unit -> modelMapper.map(unit, HousingUnitDTO.class))
                .toList();
    }

    public List<HousingUnitDTO> getAllData() throws HousingUnitNotFoundException {
        List<HousingUnit> list = housingUnitRepository.findAll();
        if (list.isEmpty()) {
            throw new HousingUnitNotFoundException(EMPTY_PROJECT);
        }
        return list.stream()
                .map(unit -> modelMapper.map(unit, HousingUnitDTO.class))
                .toList();
    }
}
