package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.entity.UserSchedule;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import com.sparta.scheduleserver.repository.UserScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserScheduleService {

    private final UserScheduleRepository userScheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public UserScheduleService(UserScheduleRepository userScheduleRepository,
                               UserRepository userRepository,
                               ScheduleRepository scheduleRepository) {
        this.userScheduleRepository = userScheduleRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void assignScheduleToUser(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 아이디가 없습니다: " + userId));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("일정 아이디가 없습니다: " + scheduleId));

        UserSchedule userSchedule = new UserSchedule(user, schedule);

        userScheduleRepository.save(userSchedule);
    }
}
