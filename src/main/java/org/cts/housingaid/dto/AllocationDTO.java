package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AllocationStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllocationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long allocationId;

    @NotNull(message="Officer ID required")
    @Positive(message="Officer ID must be positive")
    private Long officierId;

    @NotNull(message="Allocation date required")
    private LocalDate allocationDate;

    @NotNull(message="Status required")
    private AllocationStatus status;

    @NotNull(message="Housing Unit ID required")
    @Positive
    private Long housingUnitId;

    @NotNull(message="Eligibility Check ID required")
    @Positive
    private Long eligibilityCheckId;

}