package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.model.entity.dto.ScheduleExceptionAuthorDto;
import com.sparta.scheduleserver.model.entity.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.model.entity.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.model.entity.Schedule;
import com.sparta.scheduleserver.model.entity.User;
import com.sparta.scheduleserver.model.entity.UserSchedule;
import com.sparta.scheduleserver.model.entity.error.ErrorCode;
import com.sparta.scheduleserver.model.entity.repository.ScheduleRepository;
import com.sparta.scheduleserver.model.entity.repository.UserRepository;
import com.sparta.scheduleserver.model.entity.repository.UserScheduleRepository;
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
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + id));
        return new ScheduleResponseDto(schedule);
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<ScheduleExceptionAuthorDto> getAllSchedules(){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleExceptionAuthorDto> scheduleInquiry = new ArrayList<>();

        for(Schedule s: scheduleList){
            ScheduleExceptionAuthorDto dto = new ScheduleExceptionAuthorDto(
                    s.getId(),
                    s.getTitle(),
                    s.getContent()
            );
            scheduleInquiry.add(dto);
        }
        return scheduleInquiry;
    }

    // 일정 등록 메서드
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Long authorId = requestDto.getAuthorId();  // 작성자의 ID를 가져옴
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.USER_NOT_FOUND.getMessage() + authorId));  // 작성자 조회
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
                .orElseThrow(() -> new RuntimeException(ErrorCode.SCHEDULE_NOT_FOUND.getMessage() + id));
        schedule.update(requestDto.getAuthorId() ,requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    // 일정 삭제 메서드
    @Transactional
    public void deleteSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ErrorCode.SCHEDULE_NOT_FOUND.getMessage() + id));
        scheduleRepository.delete(schedule);
    }

    // 페이지네이션된 결과 조회
    @Transactional(readOnly = true) //메서드 내에서 데이터베이스와의 상호작용이 읽기 전용
    public Page<ScheduleResponseDto> getSchedules(Pageable pageable) {
        //Schedule 엔티티의 모든 데이터를 페이징하여 조회
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        List<ScheduleResponseDto> dtoList = new ArrayList<>();

        for (Schedule schedule : schedules.getContent()){
            dtoList.add(new ScheduleResponseDto(schedule));
        }

        return new PageImpl<>(dtoList, pageable, schedules.getTotalElements());
    }

    @Transactional
    public void assignUsersToSchedule(Long scheduleId, List<Long> userIds){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.SCHEDULE_NOT_FOUND.getMessage()));

        for(Long userId : userIds){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));

            UserSchedule userSchedule = new UserSchedule();
            userSchedule.setUser(user);
            userSchedule.setSchedule(schedule);

            userScheduleRepository.save(userSchedule);
        }
    }
}
