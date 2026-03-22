package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class HousingApplicationNotFoundException extends RuntimeException {

    public HousingApplicationNotFoundException() {
        super(ExceptionConstants.HOUSING_APPLICATION_NOT_FOUND);
    }
    public HousingApplicationNotFoundException(String message) {
        super(message);
    }
}