package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.HousingProjectStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HousingProjectDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Positive(message = "ID must be positive")
    private Long housingProjectId;

    @NotNull(message = "Number of houses is required")
    @Min(value = 1, message = "Minimum houses must be 1")
    private Long numberOfHouses;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters")
    private String housingProjectTitle;

    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String housingProjectDescription;

    @NotNull(message = "Start date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate housingProjectStartDate;

    @NotNull(message = "End date is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate housingProjectEndDate;

    @NotNull(message = "Budget is mandatory")
    @DecimalMin(value = "1.0", message = "Budget must be greater than 0")
    private Double housingProjectBudget;

    @NotNull(message = "Status is mandatory")
    private HousingProjectStatus housingProjectStatus;

}