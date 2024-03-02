package org.example.mypost.services.User;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.example.mypost.exception.UserNotFoundException;
import org.example.mypost.services.Auth.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFriendShipServiceImpl implements UserFriendShipService{


    private final UserRepository userRepository;
    private final UserFriendsRepository userFriendsRepository;
    private final AuthenticationService authService;



    @Override
    @Transactional
    public String saveUserFriend(int userToAddId) {
        User user2 = userRepository.findById(userToAddId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userToAddId));

        // Assuming you have a method to get the ID of the currently logged in user
        int loggedInUserId = authService.getLoggedInUserId();
        User user1 = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + loggedInUserId));

        UserFriends userFriends = new UserFriends();
        userFriends.setUser1(user1);
        userFriends.setUser2(user2);
        userFriends.setPendingFriend(user2);

        UserFriends result = userFriendsRepository.save(userFriends);

        return "user added to friends";
    }

    @Override
    public Boolean deleteUserFriendShip(int userToDeleteId) {
        //find this relationship which consists of given id and current logged in user id
        int loggedInUserId = authService.getLoggedInUserId();
        User user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + loggedInUserId));
        User userToDelete = userRepository.findById(userToDeleteId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userToDeleteId));

        UserFriends userFriends = userFriendsRepository.findFriendshipByUser1AndUser2(user, userToDelete);
        userFriendsRepository.delete(userFriends);
        return true;
    }

    @Override
    public Boolean acceptUserFriendShip(int i) {
        //find this relationship which consists of given id and current logged in user id and set status to ACCEPTED
        int loggedInUserId = authService.getLoggedInUserId();
        User user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + loggedInUserId));
        User userToAccept = userRepository.findById(i)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + i));

        UserFriends userFriends = userFriendsRepository.findFriendshipByUser1AndUser2(user, userToAccept);
        userFriends.setStatus("ACCEPTED");
        userFriendsRepository.save(userFriends);
        return true;
    }
}
