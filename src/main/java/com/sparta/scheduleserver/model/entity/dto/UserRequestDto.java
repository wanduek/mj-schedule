package com.sparta.scheduleserver.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//유저 요청 클래스
public class UserRequestDto {

    private Long id;
    private String username;
    private String password;
    private String email;

}
