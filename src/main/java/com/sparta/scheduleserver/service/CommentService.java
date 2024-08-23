package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.CommentRequestDto;
import com.sparta.scheduleserver.dto.CommentResponseDto;
import com.sparta.scheduleserver.entity.Comment;
import com.sparta.scheduleserver.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto getCommentById(long commentId) {
        Optional<Comment> commentOptional = commentRepository.findByCommentId(commentId);
        if (commentOptional.isPresent()) {
            return new CommentResponseDto(commentOptional.get());
        } else {
            throw new RuntimeException("댓글아이디를 찾을 수 없습니다." + commentId);
        }
    }

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto){
        Comment comment = new Comment(
                requestDto.getUsername(),
                requestDto.getCommentContent()
        );
          commentRepository.save(comment);
                return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(long commentId, CommentRequestDto responseDto){
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("댓글아이디를 찾을 수 없습니다.: " + commentId));
                comment.update(responseDto.getCommentContent());
                commentRepository.save(comment);
                return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("댓글 아이디를 찾을 수 없습니다.: " + commentId));
        commentRepository.delete(comment);  // 엔티티로 삭제
    }
}
