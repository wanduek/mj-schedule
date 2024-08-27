package com.sparta.scheduleserver.repository;

import com.sparta.scheduleserver.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findById(long id);
    @Query("SELECT s FROM Schedule s")
    List<Schedule> findAllSchedules();
}
