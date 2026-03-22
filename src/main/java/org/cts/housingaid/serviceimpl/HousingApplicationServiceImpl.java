package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.HousingApplicationRepository;
import org.cts.housingaid.dto.HousingApplicationDTO;
import org.cts.housingaid.entity.HousingApplication;
import org.cts.housingaid.enums.HousingApplicationStatus;
import org.cts.housingaid.exception.HousingApplicationNotFoundException;
import org.cts.housingaid.service.HousingApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.HOUSING_APPLICATION_NOT_FOUND;

@Service
@AllArgsConstructor
public class HousingApplicationServiceImpl implements HousingApplicationService {

    private final HousingApplicationRepository housingApplicationRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createHousingApplication(HousingApplicationDTO housingApplicationDTO){
        HousingApplication housingApplication = modelMapper.map(housingApplicationDTO, HousingApplication.class);
        housingApplicationRepository.save(housingApplication);
    }

    @Override
    public HousingApplicationDTO getHousingApplicationById(Long housingApplicationId) throws HousingApplicationNotFoundException{

        HousingApplication housingApplication = housingApplicationRepository.findById(housingApplicationId)
                .orElseThrow(() -> new HousingApplicationNotFoundException(HOUSING_APPLICATION_NOT_FOUND));

        return modelMapper.map(housingApplication, HousingApplicationDTO.class);
    }

    @Override
    public List<HousingApplicationDTO> getApplicationsByStatus(HousingApplicationStatus housingApplicationStatus) throws HousingApplicationNotFoundException{

        List<HousingApplication> housingApplications = housingApplicationRepository.findByHousingApplicationStatus(housingApplicationStatus);

        if(housingApplications.isEmpty()){
            throw new HousingApplicationNotFoundException(EMPTY_DATA);
        }

        return housingApplications.stream().map(app -> modelMapper.map(app, HousingApplicationDTO.class)).toList();
    }

    @Override
    public List<HousingApplicationDTO> getAllHousingApplications() throws HousingApplicationNotFoundException{

        List<HousingApplication> housingApplications = housingApplicationRepository.findAll();

        if(housingApplications.isEmpty()){
            throw new HousingApplicationNotFoundException(EMPTY_DATA);
        }

        return housingApplications.stream().map(app -> modelMapper.map(app, HousingApplicationDTO.class)).toList();
    }

    @Override
    public void deleteHousingApplication(Long housingApplicationId) throws HousingApplicationNotFoundException{

        HousingApplication housingApplication = housingApplicationRepository.findById(housingApplicationId)
                .orElseThrow(() -> new HousingApplicationNotFoundException(HOUSING_APPLICATION_NOT_FOUND));

        housingApplicationRepository.delete(housingApplication);
    }

}