package org.example.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserResponse.UserOptions.UserDto;
import org.example.mypost.Dto.UserResponse.UserListDto;
import org.example.mypost.controller.utils.UserUtils;
import org.example.mypost.entity.Posts;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.example.mypost.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController{

    final UserService userService;
    final UserUtils userUtils;

@GetMapping("/allUsers")
public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Boolean friends, @RequestParam(required = false) Boolean posts) {
    List<User> userList = userService.getAllUsers();
    UserListDto response = new UserListDto();
    List<UserDto> userDtoList = new ArrayList<>();

    for (User user : userList) {
        UserDto userDto = userUtils.getUserDto( friends , posts , user );
        userDtoList.add(userDto);
    }

    response.setUserList(userDtoList);
    return ResponseEntity.ok(response);
}
@GetMapping("/userById")
public ResponseEntity<?> getUserById(@RequestParam int id, @RequestParam(required = false) Boolean friends, @RequestParam(required = false) Boolean posts) {
    User user = userService.getUserById( id );
    UserDto userDto = userUtils.getUserDto( friends , posts , user );
    return ResponseEntity.ok( userDto );
}

@GetMapping("/userByName")
public ResponseEntity<?> getUserByName(@RequestParam String name, @RequestParam(required = false) Boolean friends, @RequestParam(required = false) Boolean posts) {
    User user = userService.getUserByName( name );
    UserDto userDto = userUtils.getUserDto( friends , posts , user );
    return ResponseEntity.ok( userDto );
}

}
