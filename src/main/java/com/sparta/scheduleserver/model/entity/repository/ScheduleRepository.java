package com.sparta.scheduleserver.model.entity.repository;

import com.sparta.scheduleserver.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    
}
