package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.entity.UserSchedule;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import com.sparta.scheduleserver.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserScheduleService {

    private final UserScheduleRepository userScheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
}
