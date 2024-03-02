package org.example.mypost.controller.utils;

import org.example.mypost.Dto.UserResponse.UserListDto;
import org.example.mypost.Dto.UserResponse.UserOptions.UserDto;
import org.example.mypost.dao.PostsRepository;
import org.example.mypost.dao.UserFriendsRepository;
import org.example.mypost.entity.Posts;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
//public class UserUtils{
//    public UserDto getUserDto(Boolean friends , Boolean posts , User user) {
//        List<UserFriends> userFriends = Boolean.TRUE.equals( friends ) ? user.getUserFriends() : null;
//        List<Posts> userPosts = Boolean.TRUE.equals( posts ) ? user.getPosts() : null;
//        UserDto userDto = UserDto.builder()
//                .id( user.getUserId())
//                .firstName( user.getFirstName())
//                .lastName( user.getLastName() )
//                .email( user.getEmail())
//                .createdAt( user.getCreatedAt())
//                .friends(userFriends)
//                .posts(userPosts)
//                .build();
//        return userDto;
//    }
//}

@Service
public class UserUtils {

    private final UserFriendsRepository userFriendsRepository;
    private final PostsRepository postsRepository;

    @Autowired
    public UserUtils(UserFriendsRepository userFriendsRepository, PostsRepository postsRepository) {
        this.userFriendsRepository = userFriendsRepository;
        this.postsRepository = postsRepository;
    }

    public UserDto getUserDto(int friends, int posts, User user, Pageable friendsPageable, Pageable postsPageable) {
        Page<UserFriends> userFriends = friends > 0 ? userFriendsRepository.findByUser(user, friendsPageable) : null;
        Page<Posts> userPosts = posts > 0 ? postsRepository.findByUser(user, postsPageable) : null;
        UserDto userDto = UserDto.builder()
                .id(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .friends(userFriends != null ? userFriends.getContent() : null)
                .posts(userPosts != null ? userPosts.getContent() : null)
                .build();
        return userDto;
    }

    public UserDto getUserDto(User user, int friends, int posts, int friendsPage, int postsPage) {
        Pageable friendsPageable =  friends > 0 ? PageRequest.of(friendsPage, friends) : null;
        Pageable postsPageable =  posts > 0 ? PageRequest.of(postsPage, posts) : null;
        return getUserDto(friends, posts, user, friendsPageable, postsPageable);
    }

    public UserListDto getUserListDto(List<User> userList, int friends, int posts, int friendsPage, int postsPage) {
        UserListDto response = new UserListDto();
        response.setUserList(userList.stream()
                .map(user -> getUserDto(user, friends, posts, friendsPage, postsPage))
                .collect( Collectors.toList()));
        return response;
    }
}