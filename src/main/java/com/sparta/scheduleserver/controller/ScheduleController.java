package com.sparta.scheduleserver.controller;

import com.sparta.scheduleserver.dto.ScheduleRequestDto;
import com.sparta.scheduleserver.dto.ScheduleResponseDto;
import com.sparta.scheduleserver.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //댓글 개수,페이지 조회
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> getSchedules(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedDate"));
        Page<ScheduleResponseDto> schedulePage = scheduleService.getSchedules(pageable);

        return new ResponseEntity<>(schedulePage, HttpStatus.OK);
    }

    //일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
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
}
