package com.sparta.scheduleserver.model.entity.repository;

import com.sparta.scheduleserver.model.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}
