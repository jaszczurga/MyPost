package org.example.mypost.dao;

import org.example.mypost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    //get users by name
    List<User> findByFirstName(String username);

    //get user by email
    Optional<User> findByEmail(String email);

    //get users by last name
    List<User> findByLastName(String lastName);


}
