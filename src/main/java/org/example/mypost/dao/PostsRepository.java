package org.example.mypost.dao;

import org.example.mypost.entity.Posts;
import org.example.mypost.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Integer>{


    Page<Posts> findByUser(User user , Pageable postsPageable);
}
