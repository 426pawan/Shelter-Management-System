package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class AuditNotFoundException extends RuntimeException {

    public AuditNotFoundException() {
        super(ExceptionConstants.AUDIT_NOT_FOUND);
    }
    public AuditNotFoundException(String message) {
        super(message);
    }
}