package com.sparta.scheduleserver.model.entity.dto;

import com.sparta.scheduleserver.model.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 댓글 응답 클래스
public class CommentResponseDto {
    private String username;
    private String commentContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 댓글 응답 생성자
    public CommentResponseDto(Comment comment) {
        this.username = comment.getUsername();
        this.commentContent = comment.getCommentContent();
        this.createdDate = comment.getCreatedDate();
        this.updatedDate = comment.getUpdatedDate();
    }
}


// 이팩티브 자바 1장

//public static CommentResponseDto entityToDto(Comment comment){}
