package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.ComplianceRecordRepository;
import org.cts.housingaid.dto.ComplianceRecordDTO;
import org.cts.housingaid.entity.ComplianceRecord;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.cts.housingaid.exception.ComplianceRecordNotFoundException;
import org.cts.housingaid.service.ComplianceRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_PROJECT;

@Service
@AllArgsConstructor
public class ComplianceRecordServiceImpl implements ComplianceRecordService {

    private final ComplianceRecordRepository complianceRecordRepository;

    public void saveRecord(ComplianceRecordDTO dto) {

        ComplianceRecord record = new ComplianceRecord();

        record.setEntityId(dto.getEntityId());
        record.setType(dto.getType());
        record.setResult(dto.getResult());
        record.setDate(dto.getDate());
        record.setNotes(dto.getNotes());

        complianceRecordRepository.save(record);

    }

    public List<ComplianceRecordDTO> getRecordsByEntityIdOrType(
            Integer entityId,
            ComplianceEntityType type)
            throws ComplianceRecordNotFoundException {

        List<ComplianceRecord> list =
                complianceRecordRepository
                        .findByEntityIdOrType(Long.valueOf(entityId), type);

        if (list.isEmpty()) {
            throw new ComplianceRecordNotFoundException(EMPTY_PROJECT);
        }

        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ComplianceRecordDTO> getAllRecords()
            throws ComplianceRecordNotFoundException {

        List<ComplianceRecord> list =
                complianceRecordRepository.findAll();

        if (list.isEmpty()) {
            throw new ComplianceRecordNotFoundException(EMPTY_PROJECT);
        }

        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ComplianceRecordDTO mapToDTO(ComplianceRecord record) {

        ComplianceRecordDTO dto =
                new ComplianceRecordDTO();

        dto.setComplianceId(record.getComplianceId());
        dto.setEntityId(record.getEntityId());
        dto.setType(record.getType());
        dto.setResult(record.getResult());
        dto.setDate(record.getDate());
        dto.setNotes(record.getNotes());

        return dto;
    }

}