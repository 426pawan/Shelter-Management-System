package org.cts.housingaid.service;

import org.cts.housingaid.dto.UsersDTO;
import org.cts.housingaid.enums.UserStatus;
import org.cts.housingaid.exception.UsersNotFoundException;

import java.util.List;

public interface UsersService {

    void updateUser(UsersDTO usersDTO) throws UsersNotFoundException;

    void createUser(UsersDTO usersDTO);

    List<UsersDTO> getAllUsers() throws UsersNotFoundException;

    UsersDTO getUserById(Long userId) throws UsersNotFoundException;

    void updateUserStatus(Long userId, UserStatus userStatus) throws UsersNotFoundException;

    void deleteUser(Long userId) throws UsersNotFoundException;

}