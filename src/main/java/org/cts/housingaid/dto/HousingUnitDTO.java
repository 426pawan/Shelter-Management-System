package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.HousingUnitStatus;
import org.cts.housingaid.enums.HousingUnitType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousingUnitDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long housingUnitId;

    @NotBlank(message="Location required")
    private String housingUnitLocation;

    @NotNull(message="Type required")
    private HousingUnitType housingUnitType;

    @NotNull(message="Capacity required")
    @Min(value=1,message="Minimum capacity 1")
    private Long housingUnitCapacity;

    @NotNull(message="Status required")
    private HousingUnitStatus housingUnitStatus;

    @NotNull(message="Resource ID required")
    private Long resourcesId;

}