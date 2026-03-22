package org.cts.housingaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.cts.housingaid.util.ExceptionConstants;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String,Object> buildResponse(String message,HttpStatus status) {
        Map<String,Object> response=new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status",status.value());
        response.put("error",status.getReasonPhrase());
        response.put("message",message);
        return response;
    }

    @ExceptionHandler(HousingProjectNotFoundException.class)
    public ResponseEntity<?> handleProjectNotFound (HousingProjectNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HousingUnitNotFoundException.class)
    public ResponseEntity<?> handleUnitNotFound(HousingUnitNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourcesNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CitizenNotFoundException.class)
    public ResponseEntity<?> handleCitizenNotFound(CitizenNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CitizenDocumentNotFoundException.class)
    public ResponseEntity<?> handleCitizenDocumentNotFound(CitizenDocumentNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HousingApplicationNotFoundException.class)
    public ResponseEntity<?> handleApplicationNotFound(HousingApplicationNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AllocationNotFoundException.class)
    public ResponseEntity<?> handleAllocationNotFound(AllocationNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuditNotFoundException.class)
    public ResponseEntity<?> handleAuditNotFound(AuditNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuditLogNotFoundException.class)
    public ResponseEntity<?> handleAuditLogNotFound(AuditLogNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ComplianceRecordNotFoundException.class)
    public ResponseEntity<?> handleComplianceNotFound(ComplianceRecordNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EligibilityCheckNotFoundException.class)
    public ResponseEntity<?> handleEligibilityNotFound(EligibilityCheckNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<?> handleNotificationNotFound(NotificationNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<?> handleReportNotFound(ReportNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UsersNotFoundException ex) {
        return new ResponseEntity<>(buildResponse(ex.getMessage(),HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error-> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(buildResponse(ExceptionConstants.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex){
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IneligibleCitizenException.class)
    public ResponseEntity<?> handleIneligibleCitizen(IneligibleCitizenException ex){
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ProjectMismatchException.class)
    public ResponseEntity<?> handleProjectMismatch(ProjectMismatchException ex){
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnitUnavailableException.class)
    public ResponseEntity<?> handleUnitUnavailable(UnitUnavailableException ex){
        return new ResponseEntity<>(buildResponse(ex.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }
}