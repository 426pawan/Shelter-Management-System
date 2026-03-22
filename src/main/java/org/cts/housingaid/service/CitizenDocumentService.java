package org.cts.housingaid.service;

import org.cts.housingaid.dto.CitizenDocumentDTO;
import org.cts.housingaid.exception.CitizenDocumentNotFoundException;
import org.cts.housingaid.exception.CitizenNotFoundException;

import java.util.List;

public interface CitizenDocumentService {

    CitizenDocumentDTO uploadDocument(Long citizenId, CitizenDocumentDTO citizenDocumentDTO) throws CitizenNotFoundException;

    void deleteDocument(Long citizenDocumentId, Long citizenId) throws CitizenDocumentNotFoundException, CitizenNotFoundException;

    CitizenDocumentDTO updateDocumentAfterRejection(Long citizenId, Long citizenDocumentId, CitizenDocumentDTO citizenDocumentDTO) throws CitizenDocumentNotFoundException;

    List<CitizenDocumentDTO> getAllDocumentsByCitizen(Long citizenId) throws CitizenNotFoundException;

}