package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
}
