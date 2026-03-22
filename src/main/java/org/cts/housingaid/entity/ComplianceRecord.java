package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.cts.housingaid.enums.ComplianceResult;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComplianceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complianceId;

    private Long entityId;

    @Enumerated(EnumType.STRING)
    private ComplianceEntityType type;

    @Enumerated(EnumType.STRING)
    private ComplianceResult result;

    private LocalDate date;

    private String notes;

}