package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.AllocationRepository;
import org.cts.housingaid.dao.EligibilityCheckRepository;
import org.cts.housingaid.dao.HousingUnitRepository;
import org.cts.housingaid.dto.AllocationRequestDTO;
import org.cts.housingaid.dto.AllocationResponseDTO;
import org.cts.housingaid.entity.Allocation;
import org.cts.housingaid.entity.Citizen;
import org.cts.housingaid.entity.EligibilityCheck;
import org.cts.housingaid.entity.HousingApplication;
import org.cts.housingaid.entity.HousingUnit;
import org.cts.housingaid.enums.AllocationStatus;
import org.cts.housingaid.enums.EligibilityCheckResult;
import org.cts.housingaid.enums.HousingUnitStatus;
import org.cts.housingaid.exception.IneligibleCitizenException;
import org.cts.housingaid.exception.ProjectMismatchException;
import org.cts.housingaid.exception.UnitUnavailableException;
import org.cts.housingaid.service.AllocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.*;

@Service
@AllArgsConstructor
public class AllocationServiceImpl implements AllocationService {

    private final AllocationRepository allocationRepository;
    private final EligibilityCheckRepository eligibilityCheckRepository;
    private final HousingUnitRepository housingUnitRepository;

    @Override
    @Transactional
    public AllocationResponseDTO createAllocation(AllocationRequestDTO request){

        if(request.getCitizenId() == null){
            throw new IllegalArgumentException(CITIZEN_NOT_FOUND);
        }

        EligibilityCheck eligibilityCheck =
                eligibilityCheckRepository
                        .findByCitizenIdAndEligibilityCheckResult(
                                request.getCitizenId(),
                                EligibilityCheckResult.ELIGIBLE
                        )
                        .orElseThrow(() ->
                                new IneligibleCitizenException(CITIZEN_NOT_ELIGIBLE));

        HousingApplication housingApplication =
                eligibilityCheck.getHousingApplication();

        Long projectId =
                housingApplication.getHousingProject()
                        .getHousingProjectId();

        if(request.getProjectId()!=null &&
                !request.getProjectId().equals(projectId)){

            throw new ProjectMismatchException(
                    PROJECT_MISMATCH);
        }

        List<HousingUnit> availableUnits =
                housingUnitRepository
                        .findByHousingProjectHousingProjectIdAndHousingUnitStatus(
                                projectId,
                                HousingUnitStatus.AVAILABLE
                        );

        if(availableUnits.isEmpty()){
            throw new UnitUnavailableException(
                    UNIT_NOT_AVAILABLE);
        }

        HousingUnit housingUnit = availableUnits.get(0);

        housingUnit.setHousingUnitStatus(
                HousingUnitStatus.ALLOCATED);

        housingUnitRepository.save(housingUnit);

        Allocation allocation = new Allocation();

        allocation.setAllocationDate(LocalDate.now());
        allocation.setStatus(AllocationStatus.ALLOCATED);
        allocation.setEligibilityCheckId(
                eligibilityCheck.getEligibilityCheckId());
        allocation.setHousingUnitId(
                housingUnit.getHousingUnitId());

        allocation.setEligibilityCheck(eligibilityCheck);
        allocation.setHousingUnit(housingUnit);

        Citizen citizen =
                housingApplication.getCitizen();

        Allocation savedAllocation =
                allocationRepository.save(allocation);

        return new AllocationResponseDTO(
                "Allocation successful",
                savedAllocation.getAllocationId(),
                citizen.getCitizenId(),
                housingUnit.getHousingUnitId(),
                projectId,
                savedAllocation.getStatus().name()
        );
    }

}