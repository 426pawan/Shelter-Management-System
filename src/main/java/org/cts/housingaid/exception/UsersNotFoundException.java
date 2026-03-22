package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class UsersNotFoundException extends RuntimeException {

    public UsersNotFoundException() {
        super(ExceptionConstants.USER_NOT_FOUND);
    }
    public UsersNotFoundException(String message) {
        super(message);
    }
}