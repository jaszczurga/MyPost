package org.example.mypost.services.User;

import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;

import java.util.List;

public interface UserService{

    List<User> getAllUsers();
    User getUserById(int id);

    List<User> getUsersByFirstName(String name);

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

//
//String saveUserFriend(int userId , int friendId);
//String saveUserFriend(UserFriends userFriends);

    String saveUserFriend(int id);


}
