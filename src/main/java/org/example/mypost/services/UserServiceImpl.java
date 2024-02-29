package org.example.mypost.services;

import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserFriendsRepository userFriendsRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

}
