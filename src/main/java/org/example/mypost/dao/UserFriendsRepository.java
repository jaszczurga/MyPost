package org.example.mypost.dao;

import org.example.mypost.entity.User;
import org.example.mypost.entity.UserFriends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserFriendsRepository extends JpaRepository<UserFriends, Integer>{


    // returns only accepted friends
    @Query("SELECT uf FROM UserFriends uf WHERE (uf.user1 = :user or uf.user2 = :user) and uf.status='ACCEPTED'")
    Page<UserFriends> findAllAcceptedFriendShipsForGivenUser(User user , Pageable friendsPageable);
}
