package org.cts.housingaid.dao;

import org.cts.housingaid.entity.ComplianceRecord;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceRecordRepository
        extends JpaRepository<ComplianceRecord, Long> {

    @Query("SELECT cr FROM ComplianceRecord cr WHERE cr.entityId = :entityId OR cr.type = :entityType")
    List<ComplianceRecord> findByEntityIdOrType(
            @Param("entityId") Long entityId,
            @Param("entityType") ComplianceEntityType type
    );

}