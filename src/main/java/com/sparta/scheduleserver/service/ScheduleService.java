package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 아이디 조회 결과 반환
    public ScheduleResponseDto getScheduleById(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정 아이디를 찾을 수 없습니다: " + id));
        return new ScheduleResponseDto(schedule);
    }

    // 전체 일정 조회
    @Transactional
    public List<ScheduleResponseDto> getAllSchedules(){
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> dtoList1 = new ArrayList<>();
        for(Schedule s: schedules){
            dtoList1.add(new ScheduleResponseDto(s));
        }
        return dtoList1;

    }

    // 일정 등록된 결과 반환
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Long authorId = requestDto.getAuthorId();  // 작성자의 ID를 가져옴
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("작성자를 찾을 수 없습니다: " + authorId));  // 작성자 조회
        // 작성자를 포함한 스케줄 생성
        Schedule schedule = new Schedule(
                author,
                requestDto.getTitle(),
                requestDto.getContent()
        );
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);  // 생성된 스케줄의 정보를 반환
    }

    // 일정 수정된 결과 반환
    @Transactional
    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        schedule.update(requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제 결과 반환
    @Transactional
    public void deleteSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        scheduleRepository.delete(schedule);
    }

    // 페이지네이션된 결과 반환
    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        List<ScheduleResponseDto> dtoList = new ArrayList<>();

        for (Schedule schedule : schedules.getContent()){
            dtoList.add(new ScheduleResponseDto(schedule));
        }

        return new PageImpl<>(dtoList, pageable, schedules.getTotalElements());
    }
}
