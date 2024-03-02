package org.example.mypost.services.User;

import org.example.mypost.entity.UserFriends;

public interface UserFriendShipService{

    String saveUserFriend(int id);


    Boolean deleteUserFriendShip(int i);

    Boolean acceptUserFriendShip(int i);

    UserFriends findRelationShipWithGivenUser(int i);
}
