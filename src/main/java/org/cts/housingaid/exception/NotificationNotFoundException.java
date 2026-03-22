package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException() {
        super(ExceptionConstants.NOTIFICATION_NOT_FOUND);
    }
    public NotificationNotFoundException(String message) {
        super(message);
    }
}