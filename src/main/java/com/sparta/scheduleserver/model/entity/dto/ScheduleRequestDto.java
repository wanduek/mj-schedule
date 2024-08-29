package com.sparta.scheduleserver.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//일정 요청 클래스
public class ScheduleRequestDto {
    private Long authorId;// 유저id에서 받아온 id
    private String title; // 일정 제목
    private String content; // 일정 내용

}

