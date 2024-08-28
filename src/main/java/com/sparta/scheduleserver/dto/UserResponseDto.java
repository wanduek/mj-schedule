package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

//유저 응답 클래스
public class UserResponseDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 일정 응답 생성자
    public UserResponseDto(User user) {
        this.id = user.getUserId();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate(); // 일정 등록일
        this.updatedDate = user.getUpdatedDate(); // 일정 수정일
    }
}
