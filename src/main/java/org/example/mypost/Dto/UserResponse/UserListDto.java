package org.example.mypost.Dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mypost.entity.User;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserListDto{
    List<?> userList;
}
