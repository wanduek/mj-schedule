package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Schedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @EntityGraph(attributePaths = {"author"})  // `author` 정보를 함께 로드
    @Override
    Optional<Schedule> findById(Long id);
}
