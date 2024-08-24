package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private String username;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ScheduleResponseDto(Schedule user) {
        this.username = user.getUsername();
        this.title = user.getTitle();
        this.content = user.getContent();
        this.createdDate = user.getCreatedDate();
        this.updatedDate = user.getUpdatedDate();
    }
}
