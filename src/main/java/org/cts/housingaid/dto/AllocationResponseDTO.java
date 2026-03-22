package org.cts.housingaid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationResponseDTO {
    private String message;
    private Long allocationId;
    private Long citizenId;
    private Long housingunitId;
    private Long housingProjectId;
    private String status;
}