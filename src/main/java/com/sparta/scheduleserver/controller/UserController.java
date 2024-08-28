package com.sparta.scheduleserver.controller;

import com.sparta.scheduleserver.config.PasswordEncoder;
import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.UserRequestDto;
import com.sparta.scheduleserver.dto.UserResponseDto;
import com.sparta.scheduleserver.jwt.JwtUtil;
import com.sparta.scheduleserver.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;



    //유저 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        //ResponseEntity로 HTTP 상태 코드, 헤더, 응답 본문 등 응답처리 가능.
        UserResponseDto responseDto = userService.createUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //유저 조회 단건
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable long id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //유저 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable long id,
            @RequestBody UserRequestDto requestDto) {
        UserResponseDto responseDto = userService.updateUser(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 사용자 정보 추출
    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile(HttpServletRequest request) {
        // 요청에서 JWT 토큰 추출
        String token = jwtUtil.getTokenFromRequest(request);

        if (token == null || !jwtUtil.validateToken(jwtUtil.substringToken(token))) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        // 토큰에서 사용자 정보 추출
        String username = jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(token)).getSubject();

        // 사용자 정보 반환 (여기서는 간단하게 사용자 이름만 반환)
        return ResponseEntity.ok("사용자 이름: " + username);
    }
}
