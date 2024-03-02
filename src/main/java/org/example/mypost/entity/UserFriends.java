package org.example.mypost.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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


    @JsonIgnoreProperties({"posts", "userFriends","role","authorities","enabled","accountNonExpired","accountNonLocked","credentialsNonExpired","username"})
    @ManyToOne()
    @JoinColumn(name = "user1_id", referencedColumnName = "user_id")
    private User user1;

    @JsonIgnoreProperties({"posts", "userFriends","role","authorities","enabled","accountNonExpired","accountNonLocked","credentialsNonExpired","username"})
    @ManyToOne()
    @JoinColumn(name = "user2_id", referencedColumnName = "user_id")
    private User user2;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "status")
    private String status = Status.PENDING.toString();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "pending_user_id", referencedColumnName = "user_id")
    private User pendingFriend;


}

enum Status{
    PENDING,
    ACCEPTED,
    REJECTED
}