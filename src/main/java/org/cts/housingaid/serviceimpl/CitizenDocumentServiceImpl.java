package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.CitizenDocumentRepository;
import org.cts.housingaid.dao.CitizenRepository;
import org.cts.housingaid.dto.CitizenDocumentDTO;
import org.cts.housingaid.entity.Citizen;
import org.cts.housingaid.entity.CitizenDocument;
import org.cts.housingaid.enums.CitizenDocumentVerificationStatus;
import org.cts.housingaid.exception.CitizenDocumentNotFoundException;
import org.cts.housingaid.exception.CitizenNotFoundException;
import org.cts.housingaid.service.CitizenDocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.CITIZEN_DOCUMENT_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.CITIZEN_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.DOCUMENT_NOT_DELETABLE;
import static org.cts.housingaid.util.ExceptionConstants.DOCUMENT_NOT_OWNED_BY_CITIZEN;

@Service
@AllArgsConstructor
public class CitizenDocumentServiceImpl implements CitizenDocumentService {

    private final CitizenRepository citizenRepository;
    private final CitizenDocumentRepository citizenDocumentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CitizenDocumentDTO uploadDocument(Long citizenId, CitizenDocumentDTO citizenDocumentDTO) throws CitizenNotFoundException {

        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(CITIZEN_NOT_FOUND));

        CitizenDocument citizenDocument = modelMapper.map(citizenDocumentDTO, CitizenDocument.class);

        citizenDocument.setCitizen(citizen);
        citizenDocument.setCitizenDocumentUploadedDate(LocalDateTime.now());
        citizenDocument.setCitizenDocumentVerificationStatus(CitizenDocumentVerificationStatus.PENDING);

        CitizenDocument savedDocument = citizenDocumentRepository.save(citizenDocument);

        return modelMapper.map(savedDocument, CitizenDocumentDTO.class);
    }

    @Override
    public void deleteDocument(Long citizenDocumentId, Long citizenId) throws CitizenDocumentNotFoundException, CitizenNotFoundException {

        CitizenDocument citizenDocument = citizenDocumentRepository.findById(citizenDocumentId)
                .orElseThrow(() -> new CitizenDocumentNotFoundException(CITIZEN_DOCUMENT_NOT_FOUND));

        if(!citizenDocument.getCitizen().getCitizenId().equals(citizenId)){
            throw new IllegalStateException(DOCUMENT_NOT_OWNED_BY_CITIZEN);
        }

        if(citizenDocument.getCitizenDocumentVerificationStatus() != CitizenDocumentVerificationStatus.PENDING){
            throw new IllegalStateException(DOCUMENT_NOT_DELETABLE);
        }

        citizenDocumentRepository.delete(citizenDocument);
    }

    @Override
    public CitizenDocumentDTO updateDocumentAfterRejection(Long citizenId, Long citizenDocumentId, CitizenDocumentDTO citizenDocumentDTO) throws CitizenDocumentNotFoundException {

        CitizenDocument citizenDocument = citizenDocumentRepository.findById(citizenDocumentId)
                .orElseThrow(() -> new CitizenDocumentNotFoundException(CITIZEN_DOCUMENT_NOT_FOUND));

        if(!citizenDocument.getCitizen().getCitizenId().equals(citizenId)){
            throw new IllegalStateException(DOCUMENT_NOT_OWNED_BY_CITIZEN);
        }

        if(citizenDocument.getCitizenDocumentVerificationStatus() != CitizenDocumentVerificationStatus.REJECTED){
            throw new IllegalStateException(DOCUMENT_NOT_DELETABLE);
        }

        citizenDocument.setCitizenDocumentFileUri(citizenDocumentDTO.getCitizenDocumentFileUri());
        citizenDocument.setCitizenDocumentUploadedDate(LocalDateTime.now());
        citizenDocument.setCitizenDocumentVerificationStatus(CitizenDocumentVerificationStatus.PENDING);

        CitizenDocument updatedDocument = citizenDocumentRepository.save(citizenDocument);

        return modelMapper.map(updatedDocument, CitizenDocumentDTO.class);
    }

    @Override
    public List<CitizenDocumentDTO> getAllDocumentsByCitizen(Long citizenId) throws CitizenNotFoundException {

        citizenRepository.findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(CITIZEN_NOT_FOUND));

        List<CitizenDocument> documents = citizenDocumentRepository.findAllByCitizenCitizenId(citizenId);

        return documents.stream().map(doc -> modelMapper.map(doc, CitizenDocumentDTO.class)).toList();
    }

}