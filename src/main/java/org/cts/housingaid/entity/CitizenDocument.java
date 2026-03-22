package org.cts.housingaid.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.CitizenDocumentDocType;
import org.cts.housingaid.enums.CitizenDocumentVerificationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CitizenDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long citizenDocumentId;

    @Enumerated(EnumType.STRING)
    private CitizenDocumentDocType citizenDocumentDocType;

    private String citizenDocumentFileUri;

    private LocalDateTime citizenDocumentUploadedDate;

    @Enumerated(EnumType.STRING)
    private CitizenDocumentVerificationStatus citizenDocumentVerificationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizenId", nullable = false)
    private Citizen citizen;

}