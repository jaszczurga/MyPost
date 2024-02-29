package org.example.mypost.dao;

import org.example.mypost.entity.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFriendsRepository extends JpaRepository<UserFriends, Integer>{


}
