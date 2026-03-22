package org.cts.housingaid.service;

import org.cts.housingaid.dto.ComplianceRecordDTO;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.cts.housingaid.exception.ComplianceRecordNotFoundException;

import java.util.List;

public interface ComplianceRecordService {

    void saveRecord(ComplianceRecordDTO dto);

    List<ComplianceRecordDTO> getRecordsByEntityIdOrType(
            Integer entityId,
            ComplianceEntityType type)
            throws ComplianceRecordNotFoundException;

    List<ComplianceRecordDTO> getAllRecords()
            throws ComplianceRecordNotFoundException;

}