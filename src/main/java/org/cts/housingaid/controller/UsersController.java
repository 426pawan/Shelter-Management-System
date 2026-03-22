package org.cts.housingaid.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.cts.housingaid.dto.UsersDTO;
import org.cts.housingaid.enums.UserStatus;
import org.cts.housingaid.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UsersDTO>> getAllUsers(){
        List<UsersDTO> list = usersService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UsersDTO usersDTO){
        usersService.createUser(usersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody UsersDTO usersDTO){
        usersDTO.setUserId(id);
        usersService.updateUser(usersDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        usersService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @GetMapping("/search-user/{id}")
    public ResponseEntity<UsersDTO> searchUserById(@PathVariable Long id){
        UsersDTO user = usersService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update-user-status/{id}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam UserStatus userStatus){
        usersService.updateUserStatus(id, userStatus);
        return ResponseEntity.status(HttpStatus.OK).body("User status updated successfully");
    }

}