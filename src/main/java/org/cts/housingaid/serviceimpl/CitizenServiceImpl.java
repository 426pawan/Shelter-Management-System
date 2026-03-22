package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.CitizenRepository;
import org.cts.housingaid.dao.UsersRepository;
import org.cts.housingaid.dto.CitizenDTO;
import org.cts.housingaid.entity.Citizen;
import org.cts.housingaid.entity.Users;
import org.cts.housingaid.exception.CitizenNotFoundException;
import org.cts.housingaid.service.CitizenService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.CITIZEN_NOT_FOUND;
import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateCitizen(CitizenDTO citizenDTO) throws CitizenNotFoundException {

        Optional<Citizen> optionalCitizen = citizenRepository.findById(citizenDTO.getCitizenId());

        if(optionalCitizen.isEmpty()){
            throw new CitizenNotFoundException(CITIZEN_NOT_FOUND);
        }

        Citizen existingCitizen = optionalCitizen.get();

        modelMapper.map(citizenDTO, existingCitizen);

        citizenRepository.save(existingCitizen);
    }

    @Override
    public CitizenDTO getCitizenById(Long citizenId) throws CitizenNotFoundException {

        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(CITIZEN_NOT_FOUND));

        return modelMapper.map(citizen, CitizenDTO.class);
    }

    @Override
    public void deleteCitizen(Long citizenId) throws CitizenNotFoundException {

        Citizen citizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new CitizenNotFoundException(CITIZEN_NOT_FOUND));

        Users users = citizen.getUser();

        usersRepository.delete(users);
    }

    @Override
    public List<CitizenDTO> getAllCitizens() throws CitizenNotFoundException {

        List<Citizen> citizens = citizenRepository.findAll();

        if(citizens.isEmpty()){
            throw new CitizenNotFoundException(EMPTY_DATA);
        }

        return citizens.stream().map(citizen -> modelMapper.map(citizen, CitizenDTO.class)).toList();
    }

}