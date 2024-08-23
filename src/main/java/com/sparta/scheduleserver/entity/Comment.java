package com.sparta.scheduleserver.entity;

import com.sparta.scheduleserver.controller.CommentController;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comment")
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String commentContent;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Comment(String username, String commentContent){
        this.username = username;
        this.commentContent = commentContent;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
        this.updatedDate = LocalDateTime.now();
    }
}