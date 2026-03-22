package org.cts.housingaid.exception;

public class CitizenDocumentNotFoundException extends RuntimeException {
    public CitizenDocumentNotFoundException(String message) {
        super(message);
    }
}
