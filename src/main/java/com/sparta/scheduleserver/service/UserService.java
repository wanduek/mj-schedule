package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ScheduleResponseDto getScheduleById(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new ScheduleResponseDto(userOptional.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

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
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.update(requestDto.getTitle(), requestDto.getContent());
        userRepository.save(user);
        return new ScheduleResponseDto(user);
    }
}
