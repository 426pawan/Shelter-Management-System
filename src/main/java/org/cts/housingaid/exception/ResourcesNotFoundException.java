package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class ResourcesNotFoundException extends RuntimeException {

    public ResourcesNotFoundException() {
        super(ExceptionConstants.RESOURCE_NOT_FOUND);
    }
    public ResourcesNotFoundException(String message) {
        super(message);
    }
}