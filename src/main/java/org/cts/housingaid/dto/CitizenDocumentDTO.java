package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.CitizenDocumentDocType;
import org.cts.housingaid.enums.CitizenDocumentVerificationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenDocumentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long citizenDocumentId;

    @NotNull(message = "Document type is required")
    private CitizenDocumentDocType citizenDocumentDocType;

    @NotBlank(message = "Document URI is required")
    @Size(min = 5, max = 500, message = "URI must be between 5 and 500 characters")
    private String citizenDocumentFileUri;

    @NotNull(message = "Uploaded date is required")
    private LocalDateTime citizenDocumentUploadedDate;

    @NotNull(message = "Verification status is required")
    private CitizenDocumentVerificationStatus citizenDocumentVerificationStatus;

    @NotNull(message = "Citizen ID is required")
    @Positive(message = "Citizen ID must be positive")
    private Long citizenId;

}