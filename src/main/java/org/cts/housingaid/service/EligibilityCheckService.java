package org.cts.housingaid.service;

import org.cts.housingaid.dto.EligibilityCheckDTO;
import org.cts.housingaid.exception.EligibilityCheckNotFoundException;

import java.util.List;

public interface EligibilityCheckService {

    void createEligibilityCheck(EligibilityCheckDTO eligibilityCheckDTO);

    EligibilityCheckDTO getEligibilityCheckById(Long eligibilityCheckId) throws EligibilityCheckNotFoundException;

    List<EligibilityCheckDTO> getAllEligibilityChecks() throws EligibilityCheckNotFoundException;

    void deleteEligibilityCheck(Long eligibilityCheckId) throws EligibilityCheckNotFoundException;

}