package org.example.mypost.dao;

import org.example.mypost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    //get user by name
    Optional<User> findByFirstName(String username);

    //get user by email
    Optional<User> findByEmail(String email);

}
