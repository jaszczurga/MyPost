package org.example.mypost.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import java.util.stream.Collectors;

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
        return ResponseEntity.ok(userUtils.getUserListDto(userService.getAllUsers(), friends, posts, friendsPage, postsPage));
    }

    @GetMapping("/userById")
    public ResponseEntity<?> getUserById(@RequestParam int id,
                                         @RequestParam(required = false, defaultValue = "0") int friends,
                                         @RequestParam(required = false, defaultValue = "0") int posts,
                                         @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                         @RequestParam(required = false, defaultValue = "0") int postsPage) {
        return ResponseEntity.ok(userUtils.getUserDto(userService.getUserById(id), friends, posts, friendsPage, postsPage));
    }

    @GetMapping("/userByFirstName")
    public ResponseEntity<?> getUserByName(@RequestParam String name,
                                           @RequestParam(required = false, defaultValue = "0") int friends,
                                           @RequestParam(required = false, defaultValue = "0") int posts,
                                           @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                           @RequestParam(required = false, defaultValue = "0") int postsPage) {
        return ResponseEntity.ok(userUtils.getUserListDto(userService.getUsersByFirstName(name), friends, posts, friendsPage, postsPage));
    }

    @GetMapping("/userByLastName")
    public ResponseEntity<?> getUserByLastName(@RequestParam String lastName,
                                               @RequestParam(required = false, defaultValue = "0") int friends,
                                               @RequestParam(required = false, defaultValue = "0") int posts,
                                               @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                               @RequestParam(required = false, defaultValue = "0") int postsPage) {
        return ResponseEntity.ok(userUtils.getUserListDto(userService.getUserByLastName(lastName), friends, posts, friendsPage, postsPage));
    }


    @GetMapping("/userByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email,
                                            @RequestParam(required = false, defaultValue = "0") int friends,
                                            @RequestParam(required = false, defaultValue = "0") int posts,
                                            @RequestParam(required = false, defaultValue = "0") int friendsPage,
                                            @RequestParam(required = false, defaultValue = "0") int postsPage) {
        return ResponseEntity.ok(userUtils.getUserDto(userService.getUserByEmail(email), friends, posts, friendsPage, postsPage));
    }


//create friend
@PostMapping("/createFriendShip")
public ResponseEntity<?> saveFriendShip(@RequestBody UserFriends uf){
    return ResponseEntity.ok(userService.saveUserFriend( uf ));
}


//    @PostMapping("/createFriendShip")
//public ResponseEntity<?> saveFriendShip(@RequestBody FriendshipRequest request){
//    return ResponseEntity.ok(userService.saveUserFriend( request.userId , request.friendId));
//}
//
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    private static class FriendshipRequest {
//
//        private int userId;
//        private int friendId;
//
//    }

}

