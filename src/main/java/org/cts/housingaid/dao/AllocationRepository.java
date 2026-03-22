package org.cts.housingaid.dao;

import org.cts.housingaid.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByCitizenCitizenId(Long citizenId);

    List<Allocation> findByHousingUnitHousingUnitId(Long housingUnitId);

    List<Allocation> findByEligibilityCheckEligibilityCheckId(Long eligibilityCheckId);

}