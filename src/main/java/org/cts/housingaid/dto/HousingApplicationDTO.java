package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.HousingApplicationStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousingApplicationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long housingApplicationId;

    @NotNull(message="Citizen ID required")
    @Positive
    private Long citizenId;

    @NotNull(message="User ID required")
    @Positive
    private Long userId;

    @NotBlank(message="Applicant name required")
    @Size(min=3,max=50)
    private String housingApplicantName;

    @NotBlank(message="Scheme name required")
    @Size(min=5,max=100)
    private String housingSchemeName;

    @NotNull(message="Project ID required")
    @Positive
    private Long housingProjectId;

    private LocalDateTime housingApplicationSubmittedDate;

    private HousingApplicationStatus housingApplicationStatus;

}