package org.cts.housingaid.service;

import org.cts.housingaid.dto.AuditDTO;
import org.cts.housingaid.exception.AuditNotFoundException;

import java.util.List;

public interface AuditService {

    void createAudit(AuditDTO auditDTO);

    void updateAudit(AuditDTO auditDTO)
            throws AuditNotFoundException;

    List<AuditDTO> getAllAudits()
            throws AuditNotFoundException;

    List<AuditDTO> getAuditsByOfficerOrScope(
            Long officerId,
            String scope)
            throws AuditNotFoundException;

}