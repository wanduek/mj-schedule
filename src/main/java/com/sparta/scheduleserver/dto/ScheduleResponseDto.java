package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 일정 응답 클래스
public class ScheduleResponseDto {

    private Long id;
    private User author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int commentCount;

    // 일정 응답 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.author = schedule.getAuthor();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate(); // 일정 등록일
        this.updatedDate = schedule.getUpdatedDate(); // 일정 수정일
        this.commentCount = schedule.getComments().size(); // 댓글 개수, 크키
    }
}
