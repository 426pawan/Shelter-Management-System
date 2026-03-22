package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.AuditLogRepository;
import org.cts.housingaid.dao.UsersRepository;
import org.cts.housingaid.dto.AuditLogDTO;
import org.cts.housingaid.entity.AuditLog;
import org.cts.housingaid.entity.Users;
import org.cts.housingaid.exception.AuditLogNotFoundException;
import org.cts.housingaid.exception.UsersNotFoundException;
import org.cts.housingaid.service.AuditLogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.cts.housingaid.util.ExceptionConstants.AUDIT_LOG_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createAuditLog(AuditLogDTO auditLogDTO) throws UsersNotFoundException {

        Users users = usersRepository.findById(auditLogDTO.getUserId())
                .orElseThrow(() -> new UsersNotFoundException(USER_NOT_FOUND));

        AuditLog auditLog = modelMapper.map(auditLogDTO, AuditLog.class);

        auditLog.setUsers(users);

        auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLogDTO> getAllAuditLogs() throws AuditLogNotFoundException {

        List<AuditLog> auditLogs = auditLogRepository.findAll();

        if(auditLogs.isEmpty()){
            throw new AuditLogNotFoundException(EMPTY_DATA);
        }

        return auditLogs.stream().map(log -> modelMapper.map(log, AuditLogDTO.class)).toList();
    }

    @Override
    public AuditLogDTO getAuditLogById(Long auditLogId) throws AuditLogNotFoundException {

        AuditLog auditLog = auditLogRepository.findById(auditLogId)
                .orElseThrow(() -> new AuditLogNotFoundException(AUDIT_LOG_NOT_FOUND));

        return modelMapper.map(auditLog, AuditLogDTO.class);
    }

    @Override
    public void deleteAuditLog(Long auditLogId) throws AuditLogNotFoundException {

        AuditLog auditLog = auditLogRepository.findById(auditLogId)
                .orElseThrow(() -> new AuditLogNotFoundException(AUDIT_LOG_NOT_FOUND));

        auditLogRepository.delete(auditLog);
    }

    @Override
    public List<AuditLogDTO> getLogsByUserId(Long userId) throws AuditLogNotFoundException {

        List<AuditLog> auditLogs = auditLogRepository.findByUsers_UserId(userId);

        if(auditLogs.isEmpty()){
            throw new AuditLogNotFoundException(EMPTY_DATA);
        }

        return auditLogs.stream().map(log -> modelMapper.map(log, AuditLogDTO.class)).toList();
    }

}