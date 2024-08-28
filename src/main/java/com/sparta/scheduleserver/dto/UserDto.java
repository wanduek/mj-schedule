package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.User;
import lombok.Getter;

@Getter
public class UserDto {
    
    private final Long userId;
    private final String username;
    private final String email;


    public UserDto(User user) {
       this.userId = user.getUserId();
       this.username = user.getUsername();
       this.email = user.getEmail();
    }
}
