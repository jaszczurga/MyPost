package org.example.mypost.services;

import org.example.mypost.entity.User;

import java.util.List;

public interface UserService{

    List<User> getAllUsers();
    User getUserById(int id);

    List<User> getUsersByFirstName(String name);

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);


}
