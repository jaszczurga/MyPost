package org.example.mypost.Dto.UserResponse.UserOptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mypost.entity.Posts;
import org.example.mypost.entity.UserFriends;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserDto{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp createdAt;

    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List<?> friends;

    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List<Posts> posts;
}
