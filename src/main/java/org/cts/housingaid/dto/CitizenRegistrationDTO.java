package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.CitizenGender;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenRegistrationDTO {

    @NotBlank(message = "User name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String userPassword;

    @NotBlank(message = "Contact info is mandatory")
    private String citizenContactInfo;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String citizenAddress;

    @NotNull(message = "Date of birth is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate citizenDOB;

    @NotNull(message = "Gender is mandatory")
    private CitizenGender citizenGender;

    private Integer userId;
    private Integer citizenId;
}