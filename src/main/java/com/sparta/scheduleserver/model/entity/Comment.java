package com.sparta.scheduleserver.model.entity;

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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String commentContent;

    // comment, schedule하고 서로 1:N, N:1로 연관됨, fetch = FetchType.LAZY 부모 엔티티(schedule)가 실제로 필요할때 까지 지연로딩함
    @ManyToOne(fetch = FetchType.LAZY)
    // 현재 엔티티와 연관된 다른 엔티티 간의 외래 키를 정의함,
    @JoinColumn(name = "schedule_id", nullable = false)
    // name = "schedule_id" : 현재 엔티티가 참조하는 부모 엔티티의 기본 키를 저장함
    // nullable = false : 외래 키 컬럼이 'NULL값을 허용하지 않음 - 무결성 유지
    private Schedule schedule;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 댓글 username, commentContent와 엔티티 schedule 생성자
    public Comment(String username, String commentContent, Schedule schedule){
        this.username = username;
        this.commentContent = commentContent;
        this.schedule = schedule;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    // 댓글 내용 수정일 수정
    public void update(String commentContent) {
        this.commentContent = commentContent;
        this.updatedDate = LocalDateTime.now();
    }
}