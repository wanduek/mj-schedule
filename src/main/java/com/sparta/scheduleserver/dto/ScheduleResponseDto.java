package com.sparta.scheduleserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonIgnoreProperties({"author"}) // author 필드를 JSON 직렬화에서 제외
// 일정 응답 클래스
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int commentCount;

    private UserDto author;
    private UserDto authorName;
    private UserDto authorEmail;

    // 일정 응답 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdDate = schedule.getCreatedDate(); // 일정 등록일
        this.updatedDate = schedule.getUpdatedDate(); // 일정 수정일
        this.commentCount = schedule.getComments().size(); // 댓글 개수, 크키

        // 유저 정보 포함
        this.author = new UserDto(schedule.getAuthor());
        this.authorName = new UserDto(schedule.getAuthor());
        this.authorEmail = new UserDto(schedule.getAuthor());
    }
}
