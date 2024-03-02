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
        uniqueConstraints = {@UniqueConstraint (columnNames = {"user1_id", "user2_id"})})
public class UserFriends {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private int friendshipId;


    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id")
    private User user1;

    @JsonIgnoreProperties({"posts"})
    @ManyToOne()
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id")
    private User user2;

    @Column(name = "created_at")
    private Timestamp createdAt;

}