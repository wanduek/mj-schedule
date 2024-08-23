package com.sparta.scheduleserver.controller;


import com.sparta.scheduleserver.dto.CommentRequestDto;
import com.sparta.scheduleserver.dto.CommentResponseDto;
import com.sparta.scheduleserver.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //일정 등록
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto){
        CommentResponseDto responseDto = commentService.createComment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //일정 조회 단건
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable("id") long commentId){
        CommentResponseDto responseDto = commentService.getCommentById(commentId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    //일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable("id") long commentId,  // 경로 변수 이름 수정
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") long commentId) {  // 파라미터 수정
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
