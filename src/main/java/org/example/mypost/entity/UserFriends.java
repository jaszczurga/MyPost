package org.example.mypost.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "user_friends",
        uniqueConstraints = {@UniqueConstraint (columnNames = {"user_id", "friend_id"})})
public class UserFriends {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private int friendshipId;


    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @JsonIgnoreProperties({"userFriends","posts"})
    @ManyToOne()
    @JoinColumn(name = "friend_email", referencedColumnName = "email")
    private User friend;

    @Column(name = "created_at")
    private Timestamp createdAt;

}