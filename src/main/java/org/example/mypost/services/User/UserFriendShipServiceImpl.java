package org.example.mypost.services.User;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.dao.UserRepository;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.example.mypost.exception.MethodNotAllowed;
import org.example.mypost.exception.FriendShipAlreadyExistsException;
import org.example.mypost.exception.UserNotFoundException;
import org.example.mypost.services.Auth.AuthenticationService;
import org.example.mypost.ws.WSService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFriendShipServiceImpl implements UserFriendShipService{


    private final UserRepository userRepository;
    private final UserFriendsRepository userFriendsRepository;
    private final AuthenticationService authService;
    private final WSService wsService;
    private final UserService userService;



    @Override
    @Transactional
    public String saveUserFriend(int userToAddId) {
        User user2 = userRepository.findById(userToAddId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userToAddId));

        // Assuming you have a method to get the ID of the currently logged in user
        int loggedInUserId = authService.getLoggedInUserId();
        User user1 = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + loggedInUserId));

        //check if we try to be friend with ourselves
        if(user1.getUserId() == user2.getUserId())
            throw new MethodNotAllowed( "You cannot be friend with yourself" );

        // Check if the friendship already exists
        UserFriends existingFriendship = userFriendsRepository.findFriendshipByUser1AndUser2(user1, user2);
        if (existingFriendship != null) {
            throw new FriendShipAlreadyExistsException( "Friendship already exists" );
        }

        UserFriends userFriends = new UserFriends();
        userFriends.setUser1(user1);
        userFriends.setUser2(user2);
        userFriends.setPendingFriend(user2);

        UserFriends result = userFriendsRepository.save(userFriends);
        //wsService.notifyUser(String.valueOf(userToAddId), "you have been invited to friendlist by  " + userService.getUserById(authService.getLoggedInUserId()).getFirstName() + " " + userService.getUserById(authService.getLoggedInUserId()).getEmail());

        wsService.notifyUserForFriendRequest(String.valueOf(userToAddId), "you have been invited to friendlist by  " + userService.getUserById(authService.getLoggedInUserId()).getFirstName() + " " + userService.getUserById(authService.getLoggedInUserId()).getEmail());
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
        if(userFriends.getPendingFriend() != user)
            throw new MethodNotAllowed( "you are not allowed to accept this friendShip request because you are not pending user" );
        if(userFriends.getStatus().equals("accepted"))
            throw new MethodNotAllowed( "You cannot accept this friend request because you are already friends" );
        userFriends.setStatus("ACCEPTED");
        userFriendsRepository.save(userFriends);
        wsService.acceptFriendRequest(String.valueOf(i), "you have been accepted to friendlist by  " +  userService.getUserById(authService.getLoggedInUserId()).getFirstName() + " " + userService.getUserById(authService.getLoggedInUserId()).getEmail());
        //wsService.notifyUser(String.valueOf(i), "you have been accepted to friendlist by  " + userService.getUserById(authService.getLoggedInUserId()).getFirstName() + " " + userService.getUserById(authService.getLoggedInUserId()).getEmail());
        return true;
    }

    @Override
    public UserFriends findRelationShipWithGivenUser(int i) {
        int loggedInUserId = authService.getLoggedInUserId();
        User user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + loggedInUserId));
        User userToGet = userRepository.findById(i)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + i));

        return userFriendsRepository.findFriendshipByUser1AndUser2(user, userToGet);
    }

}
