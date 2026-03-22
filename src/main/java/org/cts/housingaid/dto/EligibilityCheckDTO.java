package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.EligibilityCheckResult;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityCheckDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long eligibilityCheckId;

    @NotNull(message="Housing Application ID required")
    @Positive(message="ID must be positive")
    private Long housingApplicationId;

    @NotNull(message="Officer ID required")
    @Positive(message="Officer ID must be positive")
    private Long officerId;

    @NotNull(message="Result required")
    private EligibilityCheckResult eligibilityCheckResult;

    @NotNull(message="Check date required")
    private LocalDate eligibilityCheckDate;

    @NotBlank(message="Notes required")
    @Size(min=5,max=500)
    private String eligibilityCheckNotes;

}