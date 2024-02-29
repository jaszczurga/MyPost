package org.example.mypost.services.User;

import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.example.mypost.exception.UserNotFoundException;
import org.example.mypost.services.User.UserService;
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

       User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException( "User not found with id: " + id));
       return user;
    }

    @Override
    public List<User> getUsersByFirstName(String name) {
        List<User> user = userRepository.findByFirstName(name);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException( "User not found with email: " + email));
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        List<User> user = userRepository.findByLastName(lastName);
        return user;
    }

}
