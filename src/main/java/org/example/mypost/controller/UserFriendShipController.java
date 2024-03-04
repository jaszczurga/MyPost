package org.example.mypost.controller;


import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserRequest.AddUserRequestDto;
import org.example.mypost.controller.utils.UserUtils;
import org.example.mypost.services.User.UserFriendShipService;
import org.example.mypost.services.User.UserService;
import org.example.mypost.ws.WSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/friendship")
@RequiredArgsConstructor
public class UserFriendShipController{

    final UserService userService;
    final UserFriendShipService userFriendShipService;
    final UserUtils userUtils;
    final WSService wsService;

    //create friend still in pending status from user added
    @PostMapping ("/createFriendShip/{id}")
    public ResponseEntity<?> saveFriendShip(@PathVariable int id){
        return ResponseEntity.ok(userFriendShipService.saveUserFriend( id ));
    }

    //delete friendShip which is equal to rejecting invitation from user added
    @DeleteMapping ("/deleteFriendShip/{id}")
    public ResponseEntity<?> deleteFriendShip(@PathVariable int id){
        return ResponseEntity.ok(userFriendShipService.deleteUserFriendShip( id ));
    }

    //accept friendShip which is equal to accepting invitation from user added and changing status of friendship to accepted
    @PutMapping ("/acceptFriendShip/{id}")
    public ResponseEntity<?> acceptFriendShip(@PathVariable int id){
        return ResponseEntity.ok(userFriendShipService.acceptUserFriendShip( id ));
    }

    //get relationship for given user
    @GetMapping ("/getRelationShip/{id}")
    public ResponseEntity<?> getRelationship(@PathVariable int id){
        return ResponseEntity.ok(userFriendShipService.findRelationShipWithGivenUser( id ));
    }


}
