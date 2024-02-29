package org.example.mypost.Dto.UserResponse;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserWithoutFriendsDto{
    private int id;
    private String name;
    private String email;
    private Timestamp createdAt;

}
