package org.example.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserResponse.UserWithoutFriendsDto;
import org.example.mypost.Dto.UserResponse.UserListDto;
import org.example.mypost.entity.User;
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

    //get all users method
   @GetMapping("/allUsers")
public ResponseEntity<?> getAllUsers(@RequestParam (required = false) Boolean friends) {
    List<User> userList;
    if (Boolean.TRUE.equals(friends)) {
        userList = userService.getAllUsers();
        UserListDto response = new UserListDto(userList);
        return ResponseEntity.ok(response);

    } else {
        userList = userService.getAllUsers();
        UserListDto response = new UserListDto();
        List<UserWithoutFriendsDto> userWithoutFriendsDtoList = new ArrayList<>();
        for (User user : userList) {
            UserWithoutFriendsDto userWithoutFriendsDto = new UserWithoutFriendsDto(user.getUserId(), user.getUsername(), user.getEmail(),user.getCreatedAt());
            userWithoutFriendsDtoList.add( userWithoutFriendsDto );
        }
        response.setUserList( userWithoutFriendsDtoList );
        return ResponseEntity.ok(response);
    }
}

}
