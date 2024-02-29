package org.example.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserResponeDto;
import org.example.mypost.entity.User;
import org.example.mypost.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController{

    final UserService userService;

    //get all users method
    @GetMapping ("/allUsers")
    public ResponseEntity<UserResponeDto> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        UserResponeDto userResponeDto = new UserResponeDto(userList);
        return ResponseEntity.ok(userResponeDto);
    }

}
