package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.CitizenRepository;
import org.cts.housingaid.dao.UsersRepository;
import org.cts.housingaid.dto.CitizenRegistrationDTO;
import org.cts.housingaid.entity.Citizen;
import org.cts.housingaid.entity.Users;
import org.cts.housingaid.enums.CitizenStatus;
import org.cts.housingaid.enums.UserRole;
import org.cts.housingaid.enums.UserStatus;
import org.cts.housingaid.exception.UserAlreadyExistsException;
import org.cts.housingaid.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.cts.housingaid.util.ExceptionConstants.USER_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository usersRepository;
    private final CitizenRepository citizenRepository;

    @Override
    @Transactional
    public CitizenRegistrationDTO registerCitizen(CitizenRegistrationDTO citizenRegistrationDTO){

        if(usersRepository.findByUserEmail(citizenRegistrationDTO.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        }

        Users users = new Users();

        users.setUserName(citizenRegistrationDTO.getUserName());
        users.setUserEmail(citizenRegistrationDTO.getUserEmail());
        users.setUserPassword(citizenRegistrationDTO.getUserPassword());
        users.setUserPhone(citizenRegistrationDTO.getCitizenContactInfo());
        users.setUserRole(UserRole.CITIZEN);
        users.setUserStatus(UserStatus.ACTIVE);

        Users savedUsers = usersRepository.save(users);

        Citizen citizen = new Citizen();

        citizen.setCitizenDOB(citizenRegistrationDTO.getCitizenDOB());
        citizen.setCitizenGender(citizenRegistrationDTO.getCitizenGender());
        citizen.setCitizenAddress(citizenRegistrationDTO.getCitizenAddress());
        citizen.setCitizenContactInfo(citizenRegistrationDTO.getCitizenContactInfo());
        citizen.setCitizenStatus(CitizenStatus.PENDING);
        citizen.setUser(savedUsers);

        Citizen savedCitizen = citizenRepository.save(citizen);

        citizenRegistrationDTO.setUserId(Math.toIntExact(savedUsers.getUserId()));
        citizenRegistrationDTO.setCitizenId(Math.toIntExact(savedCitizen.getCitizenId()));

        return citizenRegistrationDTO;
    }

}