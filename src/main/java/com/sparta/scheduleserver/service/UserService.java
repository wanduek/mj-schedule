package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.UserRequestDto;
import com.sparta.scheduleserver.dto.UserResponseDto;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 아이디 조회
    public UserResponseDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저 아이디를 찾을 수 없습니다.: " + id));
        return new UserResponseDto(user);
    }

    // 유저 생성 메서드
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(
                requestDto.getUsername(),
                requestDto.getEmail()
        );
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    // 유저 수정 메서드
    @Transactional
    public UserResponseDto updateUser(long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저 아이디를 찾을 수 없습니다.: " + id));

        // 사용자 정보 업데이트
        user.update(requestDto.getUsername(), requestDto.getEmail());
        userRepository.save(user);

        return new UserResponseDto(user);
    }

    // 유저 삭제 메서드
    @Transactional
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저 아이디를 찾을 수 없습니다.: " + id));
        userRepository.delete(user);
    }
}
