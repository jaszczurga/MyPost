package org.example.mypost.controller;


import lombok.RequiredArgsConstructor;
import org.example.mypost.Dto.UserRequest.AddUserRequestDto;
import org.example.mypost.controller.utils.UserUtils;
import org.example.mypost.services.User.UserFriendShipService;
import org.example.mypost.services.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/friendship")
@RequiredArgsConstructor
public class UserFriendShipController{

    final UserService userService;
    final UserFriendShipService userFriendShipService;
    final UserUtils userUtils;

    //create friend still in pending status from user added
    @PostMapping ("/createFriendShip")
    public ResponseEntity<?> saveFriendShip(@RequestBody AddUserRequestDto userToAddId){
        return ResponseEntity.ok(userFriendShipService.saveUserFriend( Integer.parseInt( userToAddId.getId() ) ));
    }

    //delete friendShip which is equal to rejecting invitation from user added
    @DeleteMapping ("/deleteFriendShip")
    public ResponseEntity<?> deleteFriendShip(@RequestBody AddUserRequestDto userToDeleteId){
        return ResponseEntity.ok(userFriendShipService.deleteUserFriendShip( Integer.parseInt( userToDeleteId.getId() ) ));
    }

    //accept friendShip which is equal to accepting invitation from user added and changing status of friendship to accepted
    @PutMapping ("/acceptFriendShip")
    public ResponseEntity<?> acceptFriendShip(@RequestBody AddUserRequestDto userToAcceptId){
        return ResponseEntity.ok(userFriendShipService.acceptUserFriendShip( Integer.parseInt( userToAcceptId.getId() ) ));
    }

    //get relationship for given user
    @GetMapping ("/getRelationShip")
    public ResponseEntity<?> getRelationship(@RequestBody AddUserRequestDto userToGetId){
        return ResponseEntity.ok(userFriendShipService.findRelationShipWithGivenUser( Integer.parseInt( userToGetId.getId() ) ));
    }


}
