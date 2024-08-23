package com.sparta.scheduleserver.dto;

import com.sparta.scheduleserver.entity.Comment;
import com.sparta.scheduleserver.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String username;
    private String commentContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUsername();
        this.commentContent = comment.getCommentContent();
        this.createdDate = comment.getCreatedDate();
        this.updatedDate = comment.getUpdatedDate();
    }
}
