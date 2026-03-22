package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class ComplianceRecordNotFoundException extends RuntimeException {

    public ComplianceRecordNotFoundException() {
        super(ExceptionConstants.COMPLIANCE_NOT_FOUND);
    }
    public ComplianceRecordNotFoundException(String message) {
        super(message);
    }
}