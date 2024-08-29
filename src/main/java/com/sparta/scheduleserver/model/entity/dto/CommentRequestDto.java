package com.sparta.scheduleserver.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 댓글 요청 클래스
public class CommentRequestDto{
    private Long id; //schedule 고유식별자 아이디
    private String username; // 댓글 이름 등록
    private String commentContent; // 댓글 내용 등록
}