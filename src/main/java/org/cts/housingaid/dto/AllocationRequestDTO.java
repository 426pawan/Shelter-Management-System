package org.cts.housingaid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationRequestDTO {
    private Long citizenId;
    private Long housingunitId;
    private Long projectId;
}