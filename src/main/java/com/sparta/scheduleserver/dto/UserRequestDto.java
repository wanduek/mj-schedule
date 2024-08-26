package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//유저 요청 클래스
public class UserRequestDto {

    private Long id;
    private String username;
    private String email;

}
