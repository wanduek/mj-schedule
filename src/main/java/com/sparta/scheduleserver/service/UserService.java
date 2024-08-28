package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.config.PasswordEncoder;
import com.sparta.scheduleserver.dto.UserRequestDto;
import com.sparta.scheduleserver.dto.UserResponseDto;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.jwt.JwtUtil;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // 유저 아이디 조회
    public UserResponseDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저 아이디를 찾을 수 없습니다.: " + id));
        return new UserResponseDto(user);
    }

    // 사용자 프로필 조회 메서드
    public UserResponseDto getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return new UserResponseDto(user);

    }

    // 유저 생성 메서드
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {

        //회원 확인
        String username = requestDto.getUsername();
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복되는 사용자입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()){
            throw new IllegalArgumentException("중복된 Email입니다.");
        }

        User user = new User(username, encodedPassword, email);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    // 유저 수정 메서드
    @Transactional
    public UserResponseDto updateUser(long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저 아이디를 찾을 수 없습니다.: " + id));

        if(requestDto.getPassword() !=null){
            String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
            user.setPassword(encodedPassword);
        }

        // 사용자 정보 업데이트
        user.update(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail());
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
