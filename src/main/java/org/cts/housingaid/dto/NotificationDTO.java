package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.NotificationCategory;
import org.cts.housingaid.enums.NotificationStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long notificationId;

    @NotNull(message="User ID required")
    @Positive
    private Long usersId;

    @NotNull(message="Entity ID required")
    @Positive
    private Long entityId;

    @NotBlank(message="Message required")
    @Size(min=5,max=300)
    private String notificationMessage;

    @NotNull(message="Category required")
    private NotificationCategory notificationCategory;

    @NotNull(message="Status required")
    private NotificationStatus notificationStatus;

    @NotNull(message="Created date required")
    private LocalDate notificationCreatedDate;

}