package org.cts.housingaid.dao;

import org.cts.housingaid.entity.EligibilityCheck;
import org.cts.housingaid.enums.EligibilityCheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EligibilityCheckRepository extends JpaRepository<EligibilityCheck, Long> {

    Optional<EligibilityCheck>
    findByHousingApplicationCitizenCitizenIdAndEligibilityCheckResult(
            Long citizenId,
            EligibilityCheckResult eligibilityCheckResult
    );

    List<EligibilityCheck>
    findByEligibilityCheckResult(
            EligibilityCheckResult eligibilityCheckResult
    );

    Optional<EligibilityCheck>
    findByHousingApplicationHousingApplicationId(
            Long housingApplicationId
    );

    Optional<EligibilityCheck> findByCitizenIdAndEligibilityCheckResult(Long citizenId, EligibilityCheckResult eligibilityCheckResult);
}