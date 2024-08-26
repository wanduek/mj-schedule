package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//일정 요청 클래스
public class ScheduleRequestDto {
    private Long id; // schedule 고유아이디
    private User author; // 일정 아이디 등록
    private String title; // 일정 제목
    private String content; // 일정 내용
}

