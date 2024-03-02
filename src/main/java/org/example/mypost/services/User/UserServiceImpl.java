package org.example.mypost.services.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
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

        User user = userRepository.findById( id ).orElseThrow( () -> new UserNotFoundException( "User not found with id: " + id ) );
        return user;
    }

    @Override
    public List<User> getUsersByFirstName(String name) {
        List<User> user = userRepository.findByFirstName( name );
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail( email ).orElseThrow( () -> new UserNotFoundException( "User not found with email: " + email ) );
        return user;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        List<User> user = userRepository.findByLastName( lastName );
        return user;
    }

        @Override
@Transactional
public String saveUserFriend(UserFriends userFriends) {
    User user = userRepository.findByEmail(userFriends.getUser1().getEmail())
        .orElseThrow(() -> new UserNotFoundException("User not found with email: " + userFriends.getUser1().getEmail()));
    User friend = userRepository.findByEmail(userFriends.getUser2().getEmail())
        .orElseThrow(() -> new UserNotFoundException("User not found with email: " + userFriends.getUser2().getEmail()));


    userFriends.setUser1(user);
    userFriends.setUser2(friend);


     UserFriends result = userFriendsRepository.save(userFriends);
//
//    user.getUserFriends().add(result);
//    userRepository.save(user);
//
//    friend.getUserFriends().add(result);
//    userRepository.save(friend);

    return "user added to friends";
}

//    @Transactional
//    public String saveUserFriend(int userId , int friendId) {
//        User user = userRepository.findById( userId )
//                .orElseThrow( () -> new RuntimeException( "User not found" ) );
//        User friend = userRepository.findById( friendId )
//                .orElseThrow( () -> new RuntimeException( "Friend not found" ) );
//
//        user.getUserFriends().add( friend );
//        friend.getUserFriends().add( user );
//
//        userRepository.save( user );
//        userRepository.save( friend );
//        return "user added to friends";
//    }

}
