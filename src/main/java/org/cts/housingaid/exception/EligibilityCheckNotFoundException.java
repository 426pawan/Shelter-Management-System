package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class EligibilityCheckNotFoundException extends RuntimeException {

    public EligibilityCheckNotFoundException() {
        super(ExceptionConstants.ELIGIBILITY_NOT_FOUND);
    }
    public EligibilityCheckNotFoundException(String message) {
        super(message);
    }
}