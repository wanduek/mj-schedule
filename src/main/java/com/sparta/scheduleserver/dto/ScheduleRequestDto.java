package com.sparta.scheduleserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String username;
    private String title;
    private String content;
}
