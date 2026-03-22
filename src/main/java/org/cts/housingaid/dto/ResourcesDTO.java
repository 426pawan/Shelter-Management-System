package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.ResourcesStatus;
import org.cts.housingaid.enums.ResourcesType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long resourcesId;

    @NotNull(message = "Resource type is mandatory")
    private ResourcesType resourcesType;

    @NotNull(message = "Resource status is mandatory")
    private ResourcesStatus resourcesStatus;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Long resourcesQuantity;

    @NotNull(message = "Housing Project ID is required")
    private Long housingProjectId;

}