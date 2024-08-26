package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.scheduleserver.entity.UserSchedule;

import java.util.Optional;

public interface UserScheduleRepository extends JpaRepository <UserSchedule, Long> {
    Optional<UserSchedule> findByUserAndSchedule(User user, Schedule schedule);
}
