package com.sparta.scheduleserver.controller;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.UserRequestDto;
import com.sparta.scheduleserver.dto.UserResponseDto;
import com.sparta.scheduleserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //일정 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.createUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //일정 조회 단건
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable long id,
            @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
