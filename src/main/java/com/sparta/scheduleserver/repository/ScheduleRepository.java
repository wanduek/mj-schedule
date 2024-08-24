package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByUsername(String username);
    Optional<Schedule> findByTitle(String title);
    Optional<Schedule> findByContent(String content);
    Optional<Schedule> findById(long id);
}
