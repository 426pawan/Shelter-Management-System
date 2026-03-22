package org.cts.housingaid.util;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AuditLogStatus;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AuditLogResource {

    private int usersId;

    private AuditLogStatus auditLogStatus;

    private LocalDateTime logInDateAndTime;

    private LocalDateTime logOutDateAndTime;
}