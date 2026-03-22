package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.ComplianceEntityType;
import org.cts.housingaid.enums.ComplianceResult;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplianceRecordDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long complianceId;

    @NotNull(message="Entity ID required")
    @Positive
    private Long entityId;

    @NotNull(message="Entity type required")
    private ComplianceEntityType type;

    @NotNull(message="Result required")
    private ComplianceResult result;

    @NotNull(message="Date required")
    private LocalDate date;

    @NotBlank(message="Notes required")
    @Size(min=5,max=500)
    private String notes;

}