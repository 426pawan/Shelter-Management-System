package org.cts.housingaid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cts.housingaid.enums.AuditStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long auditId;

    @NotNull(message="Officer ID required")
    @Positive
    private Long officerId;

    @NotBlank(message="Scope required")
    @Size(min=5,max=200)
    private String scope;

    @NotBlank(message="Findings required")
    @Size(min=10,max=1000)
    private String findings;

    @NotNull(message="Date required")
    private LocalDate date;

    @NotNull(message="Status required")
    private AuditStatus status;

}
