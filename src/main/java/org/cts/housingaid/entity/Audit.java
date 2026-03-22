package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AuditStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    private Long officerId;

    private String scope;

    private String findings;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AuditStatus status;

}