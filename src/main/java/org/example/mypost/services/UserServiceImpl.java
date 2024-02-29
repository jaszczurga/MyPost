package org.example.mypost.services;

import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
