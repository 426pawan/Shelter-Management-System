package org.cts.housingaid.service;

import org.cts.housingaid.dto.AllocationRequestDTO;
import org.cts.housingaid.dto.AllocationResponseDTO;

public interface AllocationService {

    AllocationResponseDTO createAllocation(AllocationRequestDTO allocationRequestDTO);

}