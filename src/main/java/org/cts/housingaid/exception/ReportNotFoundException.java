package org.cts.housingaid.exception;

import org.cts.housingaid.util.ExceptionConstants;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException() {
        super(ExceptionConstants.REPORT_NOT_FOUND);
    }
    public ReportNotFoundException(String message) {
        super(message);
    }
}