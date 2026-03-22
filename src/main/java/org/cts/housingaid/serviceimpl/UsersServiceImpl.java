package org.cts.housingaid.serviceimpl;

import lombok.AllArgsConstructor;
import org.cts.housingaid.dao.UsersRepository;
import org.cts.housingaid.dto.UsersDTO;
import org.cts.housingaid.entity.Users;
import org.cts.housingaid.enums.UserStatus;
import org.cts.housingaid.exception.UsersNotFoundException;
import org.cts.housingaid.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.cts.housingaid.util.ExceptionConstants.EMPTY_DATA;
import static org.cts.housingaid.util.ExceptionConstants.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Override
    public void updateUser(UsersDTO usersDTO) throws UsersNotFoundException {

        Optional<Users> optionalUsers = usersRepository.findById(usersDTO.getUserId());
        if(optionalUsers.isEmpty()){
            throw new UsersNotFoundException(USER_NOT_FOUND);
        }
        Users existingUsers = optionalUsers.get();
        modelMapper.map(usersDTO, existingUsers);
        usersRepository.save(existingUsers);
    }

    @Override
    public void createUser(UsersDTO usersDTO){

        if(usersRepository.findByUserEmail(usersDTO.getUserEmail()).isPresent()){
            throw new RuntimeException("User already exists with given email");
        }
        Users users = modelMapper.map(usersDTO, Users.class);
        usersRepository.save(users);
    }

    @Override
    public List<UsersDTO> getAllUsers() throws UsersNotFoundException {

        List<Users> users = usersRepository.findAll();
        if(users.isEmpty()){
            throw new UsersNotFoundException(EMPTY_DATA);
        }
        return users.stream().map(user -> modelMapper.map(user, UsersDTO.class)).toList();
    }

    @Override
    public UsersDTO getUserById(Long userId) throws UsersNotFoundException {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new UsersNotFoundException(USER_NOT_FOUND));
        return modelMapper.map(users, UsersDTO.class);
    }

    @Override
    public void updateUserStatus(Long userId, UserStatus userStatus) throws UsersNotFoundException {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new UsersNotFoundException(USER_NOT_FOUND));
        users.setUserStatus(userStatus);
        usersRepository.save(users);
    }

    @Override
    public void deleteUser(Long userId) throws UsersNotFoundException {

        Users users = usersRepository.findById(userId).orElseThrow(() -> new UsersNotFoundException(USER_NOT_FOUND));
        usersRepository.delete(users);
    }

}