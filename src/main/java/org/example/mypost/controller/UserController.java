package org.example.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserResponse.UserOptions.UserDto;
import org.example.mypost.Dto.UserResponse.UserListDto;
import org.example.mypost.controller.utils.UserUtils;
import org.example.mypost.entity.User;
import org.example.mypost.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

@GetMapping("/userByEmail")
public ResponseEntity<?> getUserByEmail(@RequestParam String email, @RequestParam(required = false) Boolean friends, @RequestParam(required = false) Boolean posts) {
    User user = userService.getUserByEmail( email );
    UserDto userDto = userUtils.getUserDto( friends , posts , user );
    return ResponseEntity.ok( userDto );
}


//@GetMapping("/userByFriend")
//@GetMapping("/userByComment")
//@GetMapping("/userByPost")
//public ResponseEntity<?> getUserByPost(@RequestParam int postId, @RequestParam(required = false) Boolean friends, @RequestParam(required = false) Boolean posts) {
//    Posts post = userService.getPostById( postId );
//    User user = post.getUser();
//    UserDto userDto = userUtils.getUserDto( friends , posts , user );
//    return ResponseEntity.ok( userDto );
//}

}
