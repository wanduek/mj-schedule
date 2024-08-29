package com.sparta.scheduleserver.model.entity.dto;

import com.sparta.scheduleserver.model.entity.User;
import lombok.Getter;

@Getter
//user객체의 일부 속성을 클라이언트에게 노출하기 위한 클래스
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
