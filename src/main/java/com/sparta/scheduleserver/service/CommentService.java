package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.model.entity.dto.CommentRequestDto;
import com.sparta.scheduleserver.model.entity.dto.CommentResponseDto;
import com.sparta.scheduleserver.model.entity.Comment;
import com.sparta.scheduleserver.model.entity.Schedule;
import com.sparta.scheduleserver.model.entity.error.ErrorCode;
import com.sparta.scheduleserver.model.entity.repository.CommentRepository;
import com.sparta.scheduleserver.model.entity.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    // 댓글 조회 메서드
    public CommentResponseDto getCommentById(long commentId) {
        Optional<Comment> commentOptional = commentRepository.findByCommentId(commentId);
        if (commentOptional.isPresent()) {
            return new CommentResponseDto(commentOptional.get());
        } else {
            throw new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + commentId);
        }
    }

    // 댓글 등록 값 반환
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto){
        Long id = requestDto.getId(); //변수명은 가독성 좋게
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + id));
        Comment comment = new Comment(
                requestDto.getUsername(),
                requestDto.getCommentContent(),
                schedule
        );
          commentRepository.save(comment);
                return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(long commentId, CommentRequestDto responseDto){
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + commentId));
                comment.update(responseDto.getCommentContent());
                commentRepository.save(comment);
                return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + commentId));
        commentRepository.delete(comment);
    }
}
