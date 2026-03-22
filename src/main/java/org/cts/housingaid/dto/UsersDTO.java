package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.UserRole;
import org.cts.housingaid.enums.UserStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotBlank(message="Name required")
    @Size(min=3,max=50,message="Name must be 3-50 chars")
    private String userName;

    @NotBlank(message="Password required")
    @Size(min=6,message="Password must be at least 6 chars")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @NotBlank(message="Email required")
    @Email(message="Invalid email format")
    private String userEmail;

    @NotBlank(message="Phone required")
    @Pattern(regexp="^[0-9]{10}$", message="Phone must be 10 digits")
    private String userPhone;

    @NotNull(message="Role required")
    private UserRole userRole;

    private UserStatus userStatus;

}