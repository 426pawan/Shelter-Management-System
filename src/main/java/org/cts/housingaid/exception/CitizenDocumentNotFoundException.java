package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class CitizenDocumentNotFoundException extends RuntimeException {

    public CitizenDocumentNotFoundException() {
        super(ExceptionConstants.CITIZEN_DOCUMENT_NOT_FOUND);
    }
    public CitizenDocumentNotFoundException(String message) {
        super(message);
    }
}