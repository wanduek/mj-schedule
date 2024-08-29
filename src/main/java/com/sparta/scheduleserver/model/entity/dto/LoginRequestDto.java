package com.sparta.scheduleserver.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//로그인 요청
public class LoginRequestDto {
    private String email;
    private String password;
}
