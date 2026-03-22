package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.AuditRepository;
import org.cts.housingaid.dto.AuditDTO;
import org.cts.housingaid.entity.Audit;
import org.cts.housingaid.exception.AuditNotFoundException;
import org.cts.housingaid.service.AuditService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.AUDIT_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.EMPTY_PROJECT;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public void createAudit(AuditDTO auditDTO) {

        Audit audit = new Audit();

        mapDtoToEntity(auditDTO, audit);

        auditRepository.save(audit);
    }

    public void updateAudit(AuditDTO auditDTO)
            throws AuditNotFoundException {

        Audit audit =
                auditRepository
                        .findById(auditDTO.getAuditId())
                        .orElseThrow(() ->
                                new AuditNotFoundException(AUDIT_NOT_FOUND));

        mapDtoToEntity(auditDTO, audit);

        auditRepository.save(audit);
    }

    public List<AuditDTO> getAllAudits()
            throws AuditNotFoundException {

        List<Audit> list =
                auditRepository.findAll();

        if (list.isEmpty()) {
            throw new AuditNotFoundException(EMPTY_PROJECT);
        }

        return list.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<AuditDTO> getAuditsByOfficerOrScope(
            Long officerId,
            String scope)
            throws AuditNotFoundException {

        List<Audit> list =
                auditRepository
                        .findByOfficerIdOrScope(
                                officerId,
                                scope
                        );

        if (list.isEmpty()) {
            throw new AuditNotFoundException(AUDIT_NOT_FOUND);
        }

        return list.stream()
                .map(this::mapToDTO)
                .toList();
    }

    private AuditDTO mapToDTO(Audit audit) {

        AuditDTO dto = new AuditDTO();

        dto.setAuditId(audit.getAuditId());

        dto.setOfficerId(audit.getOfficerId());

        dto.setScope(audit.getScope());

        dto.setFindings(audit.getFindings());

        dto.setDate(audit.getDate());

        dto.setStatus(audit.getStatus());

        return dto;
    }

    private void mapDtoToEntity(
            AuditDTO dto,
            Audit entity) {

        entity.setOfficerId(dto.getOfficerId());

        entity.setScope(dto.getScope());

        entity.setFindings(dto.getFindings());

        entity.setDate(dto.getDate());

        entity.setStatus(dto.getStatus());
    }

}