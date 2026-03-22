package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class HousingProjectNotFoundException extends RuntimeException {

    public HousingProjectNotFoundException() {
        super(ExceptionConstants.HOUSING_PROJECT_NOT_FOUND);
    }
    public HousingProjectNotFoundException(String message) {
        super(message);
    }
}