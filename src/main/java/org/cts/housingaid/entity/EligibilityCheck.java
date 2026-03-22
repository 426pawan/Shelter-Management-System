package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.cts.housingaid.enums.EligibilityCheckResult;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EligibilityCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eligibilityCheckId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "housingApplicationId", nullable = false)
    private HousingApplication housingApplication;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officerId", nullable = false)
    private Users officer;

    @Enumerated(EnumType.STRING)
    private EligibilityCheckResult eligibilityCheckResult;

    private LocalDate eligibilityCheckDate;

    private String eligibilityCheckNotes;

    @ToString.Exclude
    @OneToOne(mappedBy = "eligibilityCheck", fetch = FetchType.LAZY)
    private Allocation allocation;

}