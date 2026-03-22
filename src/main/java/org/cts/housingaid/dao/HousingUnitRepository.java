package org.cts.housingaid.dao;


import org.cts.housingaid.entity.HousingUnit;
import org.cts.housingaid.enums.HousingUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingUnitRepository extends JpaRepository<HousingUnit, Integer> {
    @Query("SELECT hu FROM HousingUnit hu WHERE hu.housingUnitId = :id OR hu.housingUnitLocation = :location OR hu.housingUnitType = :type")
    List<HousingUnit> findByHousingUnitIdOrHousingUnitLocationOrHousingUnitType(@Param("id") Long housingUnitId, @Param("location") String housingUnitLocation, @Param("type") HousingUnitType housingUnitType);

}