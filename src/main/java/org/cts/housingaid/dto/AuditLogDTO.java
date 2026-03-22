package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AuditLogAction;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long auditId;

    @NotNull(message="User ID required")
    @Positive
    private Long userId;

    @NotNull(message="Action required")
    private AuditLogAction auditLogAction;

}