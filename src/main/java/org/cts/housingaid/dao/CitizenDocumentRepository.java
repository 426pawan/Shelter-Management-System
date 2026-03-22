package org.cts.housingaid.dao;

import org.cts.housingaid.entity.CitizenDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenDocumentRepository extends JpaRepository<CitizenDocument, Long> {

    List<CitizenDocument> findAllByCitizenCitizenId(Long citizenId);

}