package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class AllocationNotFoundException extends RuntimeException {

    public AllocationNotFoundException() {
        super(ExceptionConstants.ALLOCATION_NOT_FOUND);
    }
    public AllocationNotFoundException(String message) {
        super(message);
    }
}