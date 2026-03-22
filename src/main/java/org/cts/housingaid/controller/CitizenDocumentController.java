package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.CitizenDocumentDTO;
import org.cts.housingaid.service.CitizenDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen/{citizenId}/documents")
@AllArgsConstructor
public class CitizenDocumentController {

    private final CitizenDocumentService citizenDocumentService;

    @PostMapping("/upload-document")
    public ResponseEntity<CitizenDocumentDTO> uploadDocument(@PathVariable Long citizenId, @Valid @RequestBody CitizenDocumentDTO citizenDocumentDTO){
        CitizenDocumentDTO saved = citizenDocumentService.uploadDocument(citizenId, citizenDocumentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/get-all-documents")
    public ResponseEntity<List<CitizenDocumentDTO>> getCitizenDocuments(@PathVariable Long citizenId){
        List<CitizenDocumentDTO> list = citizenDocumentService.getAllDocumentsByCitizen(citizenId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/delete-document/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long citizenId, @PathVariable Long documentId){
        citizenDocumentService.deleteDocument(documentId, citizenId);
        return ResponseEntity.status(HttpStatus.OK).body("Citizen document deleted successfully");
    }

    @PutMapping("/update-document-after-rejection/{documentId}")
    public ResponseEntity<CitizenDocumentDTO> updateAfterRejection(@PathVariable Long citizenId, @PathVariable Long documentId, @Valid @RequestBody CitizenDocumentDTO citizenDocumentDTO){
        CitizenDocumentDTO updated = citizenDocumentService.updateDocumentAfterRejection(citizenId, documentId, citizenDocumentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

}