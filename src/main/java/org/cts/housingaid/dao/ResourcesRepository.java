package org.cts.housingaid.dao;

import org.cts.housingaid.entity.Resources;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Integer> {

    @Query("SELECT r FROM Resources r WHERE r.resourcesId = :id OR r.resourcesType = :type OR r.resourcesStatus = :status")
    List<Resources> findByResourcesIdOrResourcesTypeOrResourcesStatus(@Param("id") Long resourcesId, @Param("type") ResourcesType resourcesType, @Param("status") ResourcesStatus resourcesStatus);
}