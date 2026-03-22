package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AllocationStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "Allocation")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;

    private Long officierId;

    private LocalDate allocationDate;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status;

    private Long housingUnitId;

    private Long eligibilityCheckId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eligibilityCheckId", insertable = false, updatable = false)
    private EligibilityCheck eligibilityCheck;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "housingUnitId", insertable = false, updatable = false)
    private HousingUnit housingUnit;

}