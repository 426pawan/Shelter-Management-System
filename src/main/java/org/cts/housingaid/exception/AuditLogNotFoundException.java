package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class AuditLogNotFoundException extends RuntimeException {

    public AuditLogNotFoundException() {
        super(ExceptionConstants.AUDIT_LOG_NOT_FOUND);
    }
    public AuditLogNotFoundException(String message) {
        super(message);
    }
}