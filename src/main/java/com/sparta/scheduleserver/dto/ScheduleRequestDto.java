package com.sparta.scheduleserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long id;
    private String username;
    private String title;
    private String content;
}

