package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.HousingUnitRepository;
import org.cts.housingaid.dto.HousingUnitDTO;
import org.cts.housingaid.entity.HousingUnit;
import org.cts.housingaid.enums.HousingUnitType;
import org.cts.housingaid.exception.HousingUnitNotFoundException;
import org.cts.housingaid.service.HousingUnitService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_UNIT_NOT_FOUND;

@Service
@AllArgsConstructor
public class HousingUnitServiceImpl implements HousingUnitService {

    private final HousingUnitRepository housingUnitRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateHousingUnit(HousingUnitDTO housingUnitDTO) throws HousingUnitNotFoundException {
        Optional<HousingUnit> optionalHousingUnit = housingUnitRepository.findById(housingUnitDTO.getHousingUnitId());
        if(optionalHousingUnit.isEmpty()){
            throw new HousingUnitNotFoundException(HOUSING_UNIT_NOT_FOUND);
        }
        HousingUnit existingHousingUnit = optionalHousingUnit.get();
        modelMapper.map(housingUnitDTO, existingHousingUnit);
        housingUnitRepository.save(existingHousingUnit);
    }

    @Override
    public void createHousingUnit(HousingUnitDTO housingUnitDTO) {
        HousingUnit housingUnit = modelMapper.map(housingUnitDTO, HousingUnit.class);
        housingUnitRepository.save(housingUnit);
    }

    @Override
    public List<HousingUnitDTO> getSearchedByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(Long housingUnitId, String housingUnitLocation, HousingUnitType housingUnitType) throws HousingUnitNotFoundException {
        List<HousingUnit> housingUnits = housingUnitRepository.findByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(housingUnitId, housingUnitLocation, housingUnitType);
        if(housingUnits.isEmpty()){
            throw new HousingUnitNotFoundException(EMPTY_DATA);
        }
        return housingUnits.stream().map(unit -> modelMapper.map(unit, HousingUnitDTO.class)).toList();
    }

    @Override
    public List<HousingUnitDTO> getAllHousingUnits() throws HousingUnitNotFoundException {
        List<HousingUnit> housingUnits = housingUnitRepository.findAll();
        if(housingUnits.isEmpty()){
            throw new HousingUnitNotFoundException(EMPTY_DATA);
        }
        return housingUnits.stream().map(unit -> modelMapper.map(unit, HousingUnitDTO.class)).toList();
    }

}