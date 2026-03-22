package org.cts.housingaid.service;

import org.cts.housingaid.dto.AuditLogDTO;
import org.cts.housingaid.exception.AuditLogNotFoundException;
import org.cts.housingaid.exception.UsersNotFoundException;

import java.util.List;

public interface AuditLogService {

    void createAuditLog(AuditLogDTO auditLogDTO) throws UsersNotFoundException;

    List<AuditLogDTO> getAllAuditLogs() throws AuditLogNotFoundException;

    AuditLogDTO getAuditLogById(Long auditLogId) throws AuditLogNotFoundException;

    void deleteAuditLog(Long auditLogId) throws AuditLogNotFoundException;

    List<AuditLogDTO> getLogsByUserId(Long userId) throws AuditLogNotFoundException;

}