package org.cts.housingaid.service;

import org.cts.housingaid.dto.CitizenRegistrationDTO;

public interface RegistrationService {

    CitizenRegistrationDTO registerCitizen(CitizenRegistrationDTO citizenRegistrationDTO);

}