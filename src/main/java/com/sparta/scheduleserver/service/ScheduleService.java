package com.sparta.scheduleserver.service;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.entity.User;
import com.sparta.scheduleserver.entity.UserSchedule;
import com.sparta.scheduleserver.repository.ScheduleRepository;
import com.sparta.scheduleserver.repository.UserRepository;
import com.sparta.scheduleserver.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserScheduleRepository userScheduleRepository;

    // 일정 아이디 조회 결과 반환
    public ScheduleResponseDto getScheduleById(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정 아이디를 찾을 수 없습니다: " + id));
        return new ScheduleResponseDto(schedule);
    }

    // 일정 등록된 결과 반환
    @Transactional
    public ScheduleResponseDto createSchedule(User authorId, ScheduleRequestDto requestDto) {
        User author = userRepository.findById(authorId.getUserId())
                .orElseThrow(() -> new RuntimeException("작성자를 찾을 수 없습니다: " + authorId));
        Schedule schedule = new Schedule(author, requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
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
        return scheduleRepository.findAll(pageable)
                .map(ScheduleResponseDto::new);
    }

    @Transactional
    public void addAssignedUser(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));
        UserSchedule userSchedule = new UserSchedule(user, schedule);
        schedule.addUserSchedule(userSchedule);
        userScheduleRepository.save(userSchedule);
    }

    @Transactional
    public void removeAssignedUser(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("일정을 찾을 수 없습니다: " + id));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));
        UserSchedule userSchedule = userScheduleRepository.findByUserAndSchedule(user, schedule)
                .orElseThrow(() -> new RuntimeException("UserSchedule이 존재하지 않습니다."));
        schedule.removeUserSchedule(userSchedule);
        userScheduleRepository.delete(userSchedule);
    }
}
