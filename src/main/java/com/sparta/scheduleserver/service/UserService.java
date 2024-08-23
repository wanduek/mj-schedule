package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ScheduleResponseDto getScheduleById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new ScheduleResponseDto(userOptional.get());
        } else {
            throw new RuntimeException("유저의 아이디를 찾을 수 없습니다.: " + id);
        }
    }


    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        User user = new User(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContent()
        );
        userRepository.save(user);
        return new ScheduleResponseDto(user);
    }

    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저의 아이디를 찾을 수 없습니다.: " + id));
        user.update(requestDto.getTitle(), requestDto.getContent());
        userRepository.save(user);
        return new ScheduleResponseDto(user);
    }
}
