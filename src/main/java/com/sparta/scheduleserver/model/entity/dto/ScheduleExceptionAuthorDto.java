package com.sparta.scheduleserver.model.entity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleExceptionAuthorDto {

    private Long id;
    private String title;
    private String content;

    // 모든 필드를 초기화하는 생성자
    public ScheduleExceptionAuthorDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
