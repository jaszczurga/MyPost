package org.example.mypost.controller;

import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserResponse.UserOptions.UserDto;
import org.example.mypost.Dto.UserResponse.UserListDto;
import org.example.mypost.controller.utils.UserUtils;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.example.mypost.services.User.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public ResponseEntity<?> getAllUsers(@RequestParam(required = false, defaultValue = "0") int friends,
                                     @RequestParam(required = false, defaultValue = "0") int posts,
                                     @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                     @RequestParam(required = false, defaultValue = "0") int postsPage) {
    List<User> userList = userService.getAllUsers();
    UserListDto response = new UserListDto();
    List<UserDto> userDtoList = new ArrayList<>();

    Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
    Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;

    for (User user : userList) {
        UserDto userDto = userUtils.getUserDto(friends, posts, user, friendsPageable, postsPageable);
        userDtoList.add(userDto);
    }

    response.setUserList(userDtoList);
    return ResponseEntity.ok(response);
}
@GetMapping("/userById")
public ResponseEntity<?> getUserById(@RequestParam int id,
                                     @RequestParam(required = false, defaultValue = "0") int friends,
                                     @RequestParam(required = false, defaultValue = "0") int posts,
                                     @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                     @RequestParam(required = false, defaultValue = "0") int postsPage) {
    User user = userService.getUserById(id);
    Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
    Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;
    UserDto userDto = userUtils.getUserDto(friends, posts, user, friendsPageable, postsPageable);
    return ResponseEntity.ok(userDto);
}

@GetMapping("/userByFirstName")
public ResponseEntity<?> getUserByName(@RequestParam String name,
                                       @RequestParam(required = false, defaultValue = "0") int friends,
                                       @RequestParam(required = false, defaultValue = "0") int posts,
                                       @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                       @RequestParam(required = false, defaultValue = "0") int postsPage) {
    List<User> userList = userService.getUsersByFirstName( name );
    UserListDto response = new UserListDto();
    List<UserDto> userDtoList = new ArrayList<>();

    Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
    Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;

    for (User user : userList) {
        UserDto userDto = userUtils.getUserDto(friends, posts, user, friendsPageable, postsPageable);
        userDtoList.add(userDto);
    }

    response.setUserList(userDtoList);
    return ResponseEntity.ok(response);
}
@GetMapping("/userByLastName")
public ResponseEntity<?> getUserByLastName(@RequestParam String lastName,
                                           @RequestParam(required = false, defaultValue = "0") int friends,
                                           @RequestParam(required = false, defaultValue = "0") int posts,
                                           @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                           @RequestParam(required = false, defaultValue = "0") int postsPage) {
    List<User> userList = userService.getUserByLastName( lastName );
    UserListDto response = new UserListDto();
    List<UserDto> userDtoList = new ArrayList<>();

    Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
    Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;

    for (User user : userList) {
        UserDto userDto = userUtils.getUserDto(friends, posts, user, friendsPageable, postsPageable);
        userDtoList.add(userDto);
    }

    response.setUserList(userDtoList);
    return ResponseEntity.ok(response);
}
@GetMapping("/userByEmail")
public ResponseEntity<?> getUserByEmail(@RequestParam String email,
                                        @RequestParam(required = false, defaultValue = "0") int friends,
                                        @RequestParam(required = false, defaultValue = "0") int posts,
                                        @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                        @RequestParam(required = false, defaultValue = "0") int postsPage) {
    User user = userService.getUserByEmail( email );
    Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
    Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;
    UserDto userDto = userUtils.getUserDto(friends, posts, user, friendsPageable, postsPageable);
    return ResponseEntity.ok( userDto );
}

//create friend
@PostMapping("/createFriendShip")
public ResponseEntity<?> saveFriendShip(@RequestBody UserFriends uf){
    return ResponseEntity.ok(userService.saveUserFriend( uf ));
}

}
