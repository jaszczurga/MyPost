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


    //return friendship with give user1 and user2 ids
    @Query("SELECT uf FROM UserFriends uf WHERE (uf.user1 = :user1 and uf.user2 = :user2) or (uf.user1 = :user2 and uf.user2 = :user1)")
    <Optional>UserFriends findFriendshipByUser1AndUser2(User user1 , User user2);
}
