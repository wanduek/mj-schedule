package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto getScheduleById(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정 아이디를 찾을 수 없습니다.: " + id));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContent()
        );
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("유저의 아이디를 찾을 수 없습니다.: " + id));
        schedule.update(requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getSchedules(Pageable pageable){
        return scheduleRepository.findAll(pageable)
                .map(schedule -> new ScheduleResponseDto(schedule));
    }
}
