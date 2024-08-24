package com.sparta.scheduleserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comment")
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Comment(String username, String commentContent, Schedule schedule){
        this.username = username;
        this.commentContent = commentContent;
        this.schedule = schedule;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
        this.updatedDate = LocalDateTime.now();
    }

    public void setSchedule(Schedule schedule){
        this.schedule  = schedule;
    }
}