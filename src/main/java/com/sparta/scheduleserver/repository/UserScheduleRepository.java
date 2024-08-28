package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
