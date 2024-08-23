package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUsername(String username);
    Optional<Comment> findByCommentContent(String commentContent);
    Optional<Comment> findByCommentId(long commentId);
}
