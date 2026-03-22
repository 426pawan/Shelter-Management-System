package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.HousingApplicationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HousingApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long housingApplicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenId", nullable = false)
    private Citizen citizen;

    private Long userId;

    private String housingApplicantName;

    private String housingSchemeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "housingProjectId", nullable = false)
    private HousingProject housingProject;

    private LocalDateTime housingApplicationSubmittedDate;

    @Enumerated(EnumType.STRING)
    private HousingApplicationStatus housingApplicationStatus = HousingApplicationStatus.PENDING;

}