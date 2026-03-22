package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class HousingUnitNotFoundException extends RuntimeException {

    public HousingUnitNotFoundException() {
        super(ExceptionConstants.HOUSING_UNIT_NOT_FOUND);
    }
    public HousingUnitNotFoundException(String message) {
        super(message);
    }
}