package com.sparta.scheduleserver.controller;

import com.sparta.scheduleserver.dto.ScheduleExceptionAuthorDto;
import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.entity.Schedule;
import com.sparta.scheduleserver.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleExceptionAuthorDto>> getAllSchedules(){
        List<ScheduleExceptionAuthorDto> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    //일정 조회 단건
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable long id) {
        ScheduleResponseDto responseDto = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable("id") long id){
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //댓글 개수,페이지 조회
    @GetMapping("/page")
    public ResponseEntity<Page<ScheduleResponseDto>> getSchedules(
            @RequestParam(value = "page", defaultValue = "0") int page, //페이지 번호는 0부터 시작
            @RequestParam(value = "size", defaultValue = "10") int size) // 기본 페이지 크기는 10으로 설정함
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedDate"));
        Page<ScheduleResponseDto> schedulePage = scheduleService.getSchedules(pageable);
        // ScheduleService.getSchedules(pageable)에서 반환된 Page<ScheduleResponseDto>는 페이지네이션과 정렬이 적용된 데이터를 제공한다.
        return new ResponseEntity<>(schedulePage, HttpStatus.OK);

    }

    //담당 유저 등록
    @PostMapping("/{scheduleId}/assign")
    public ResponseEntity<Void> assignUsersToSchedule(
            @PathVariable Long scheduleId,
            @RequestBody List<Long> userIds)
    {
        scheduleService.assignUsersToSchedule(scheduleId, userIds);
        return ResponseEntity.ok().build();
        //성공시 :HTTPstatus 200반환, build를 통해 body없이 클라이언트에게 응답을 보냄
    }
}
