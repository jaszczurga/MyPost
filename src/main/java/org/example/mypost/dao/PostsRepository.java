package org.example.mypost.dao;

import org.example.mypost.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Integer>{


}
