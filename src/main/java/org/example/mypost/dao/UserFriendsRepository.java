package org.example.mypost.dao;

import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFriendsRepository extends JpaRepository<UserFriends, Integer>{


    @Query("SELECT uf FROM UserFriends uf WHERE uf.user1 = :user or uf.user2 = :user")
    Page<UserFriends> findByUser(User user , Pageable friendsPageable);
}
