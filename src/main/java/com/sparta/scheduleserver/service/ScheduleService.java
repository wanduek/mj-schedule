package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleExceptionAuthorDto;
import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.dto.UserDto;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.entity.UserSchedule;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import com.sparta.scheduleserver.repository.UserScheduleRepository;
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
    private final UserScheduleRepository userScheduleRepository;

    // 일정 조회 메서드
    public ScheduleResponseDto getScheduleById(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정 아이디를 찾을 수 없습니다: " + id));
        return new ScheduleResponseDto(schedule);
    }

    // 전체 일정 조회
    @Transactional
    public List<ScheduleExceptionAuthorDto> getAllSchedules(){
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleExceptionAuthorDto> dtoList1 = new ArrayList<>();

        for(Schedule s: schedules){
            ScheduleExceptionAuthorDto dto = new ScheduleExceptionAuthorDto(
                    s.getId(),
                    s.getTitle(),
                    s.getContent()
            );
            dtoList1.add(dto);
        }
        return dtoList1;
    }

    // 일정 등록 메서드
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

    // 일정 수정 메서드
    @Transactional
    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        schedule.update(requestDto.getAuthorId() ,requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제 메서드
    @Transactional
    public void deleteSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        scheduleRepository.delete(schedule);
    }

    // 페이지네이션된 결과 조회
    @Transactional(readOnly = true) //메서드 내에서 데이터베이스와의 상호작용이 읽기 전용
    public Page<ScheduleResponseDto> getSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);//Schedule 엔티티의 모든 데이터를 페이징하여 조회
        List<ScheduleResponseDto> dtoList = new ArrayList<>();

        for (Schedule schedule : schedules.getContent()){
            dtoList.add(new ScheduleResponseDto(schedule));
        }

        return new PageImpl<>(dtoList, pageable, schedules.getTotalElements());
    }

    @Transactional
    public void assignUsersToSchedule(Long scheduleId, List<Long> userIds){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        for(Long userId : userIds){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

            UserSchedule userSchedule = new UserSchedule();
            userSchedule.setUser(user);
            userSchedule.setSchedule(schedule);

            userScheduleRepository.save(userSchedule);
        }
    }
}
