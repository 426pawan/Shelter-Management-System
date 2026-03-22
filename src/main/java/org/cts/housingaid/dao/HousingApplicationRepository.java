package org.cts.housingaid.dao;

import org.cts.housingaid.entity.HousingApplication;
import org.cts.housingaid.enums.HousingApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingApplicationRepository extends JpaRepository<HousingApplication, Long> {

    List<HousingApplication> findByHousingApplicationStatus(HousingApplicationStatus housingApplicationStatus);

}