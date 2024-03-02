package org.example.mypost.Dto.UserRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class AddUserRequestDto{

   private String id;

}
