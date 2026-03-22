package org.cts.housingaid.dao;

import org.cts.housingaid.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRepository
        extends JpaRepository<Audit, Long> {

    @Query("SELECT a FROM Audit a WHERE a.officerId = :officerId OR a.scope = :scope")
    List<Audit> findByOfficerIdOrScope(
            @Param("officerId") Long officerId,
            @Param("scope") String scope
    );

}