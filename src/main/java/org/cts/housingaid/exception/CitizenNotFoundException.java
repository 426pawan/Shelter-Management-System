package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class CitizenNotFoundException extends RuntimeException {

    public CitizenNotFoundException() {
        super(ExceptionConstants.CITIZEN_NOT_FOUND);
    }
    public CitizenNotFoundException(String message) {
        super(message);
    }
}