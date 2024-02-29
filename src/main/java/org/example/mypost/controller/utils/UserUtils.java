package org.example.mypost.controller.utils;

import org.example.mypost.Dto.UserResponse.UserOptions.UserDto;
import org.example.mypost.entity.Posts;
import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUtils{
    public UserDto getUserDto(Boolean friends , Boolean posts , User user) {
        List<UserFriends> userFriends = Boolean.TRUE.equals( friends ) ? user.getUserFriends() : null;
        List<Posts> userPosts = Boolean.TRUE.equals( posts ) ? user.getPosts() : null;
        UserDto userDto = UserDto.builder()
                .id( user.getUserId())
                .name( user.getUsername())
                .email( user.getEmail())
                .createdAt( user.getCreatedAt())
                .friends(userFriends)
                .posts(userPosts)
                .build();
        return userDto;
    }
}
