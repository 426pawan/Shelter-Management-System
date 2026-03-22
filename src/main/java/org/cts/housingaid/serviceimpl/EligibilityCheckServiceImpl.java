package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.EligibilityCheckRepository;
import org.cts.housingaid.dto.EligibilityCheckDTO;
import org.cts.housingaid.entity.EligibilityCheck;
import org.cts.housingaid.exception.EligibilityCheckNotFoundException;
import org.cts.housingaid.service.EligibilityCheckService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.ELIGIBILITY_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;

@Service
@AllArgsConstructor
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EligibilityCheckRepository eligibilityCheckRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createEligibilityCheck(EligibilityCheckDTO eligibilityCheckDTO){
        EligibilityCheck eligibilityCheck = modelMapper.map(eligibilityCheckDTO, EligibilityCheck.class);
        eligibilityCheckRepository.save(eligibilityCheck);
    }

    @Override
    public EligibilityCheckDTO getEligibilityCheckById(Long eligibilityCheckId) throws EligibilityCheckNotFoundException{

        EligibilityCheck eligibilityCheck = eligibilityCheckRepository.findById(eligibilityCheckId)
                .orElseThrow(() -> new EligibilityCheckNotFoundException(ELIGIBILITY_NOT_FOUND));

        return modelMapper.map(eligibilityCheck, EligibilityCheckDTO.class);
    }

    @Override
    public List<EligibilityCheckDTO> getAllEligibilityChecks() throws EligibilityCheckNotFoundException{

        List<EligibilityCheck> eligibilityChecks = eligibilityCheckRepository.findAll();

        if(eligibilityChecks.isEmpty()){
            throw new EligibilityCheckNotFoundException(EMPTY_DATA);
        }

        return eligibilityChecks.stream().map(check -> modelMapper.map(check, EligibilityCheckDTO.class)).toList();
    }

    @Override
    public void deleteEligibilityCheck(Long eligibilityCheckId) throws EligibilityCheckNotFoundException{

        EligibilityCheck eligibilityCheck = eligibilityCheckRepository.findById(eligibilityCheckId)
                .orElseThrow(() -> new EligibilityCheckNotFoundException(ELIGIBILITY_NOT_FOUND));

        eligibilityCheckRepository.delete(eligibilityCheck);
    }

}