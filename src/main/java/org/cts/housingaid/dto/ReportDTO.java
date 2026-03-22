package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.ReportScope;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long reportId;

    @NotNull(message="Scope required")
    private ReportScope reportScope;

    @NotBlank(message="Summary required")
    @Size(min=10,max=500)
    private String reportSummary;

    @Positive(message="Application ID must be positive")
    private Long housingApplicationId;

    @Positive(message="Allocation ID must be positive")
    private Long allocationId;

    @Positive(message="Project ID must be positive")
    private Long housingProjectId;

    @NotNull(message="Generated date required")
    private LocalDate reportGeneratedDate;

}