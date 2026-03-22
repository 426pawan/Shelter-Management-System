package org.cts.housingaid.dao;

import org.cts.housingaid.entity.HousingProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HousingProjectRepository extends JpaRepository<HousingProject, Integer> {

    @Query("select hp from HousingProject hp where hp.housingProjectId=:id or hp.housingProjectTitle=:title")
    List<HousingProject> findByHousingProjectIdOrHousingProjectTitle(@Param("id") Long housingProjectId, @Param("title") String housingProjectTitle);


}