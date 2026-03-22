package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.CitizenGender;
import org.cts.housingaid.enums.CitizenStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long citizenId;

    @NotNull(message="DOB required")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate citizenDOB;

    @NotNull(message="Gender required")
    private CitizenGender citizenGender;

    @NotBlank(message="Address required")
    @Size(min=10,max=200)
    private String citizenAddress;

    @NotBlank(message="Contact info required")
    private String citizenContactInfo;

    @NotNull(message="User ID required")
    private Long userId;

    private CitizenStatus citizenStatus;

}