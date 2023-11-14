package com.academy.springlv3.controller;

import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import com.academy.springlv3.dto.teacher.TeacherResponseDto;
import com.academy.springlv3.service.TeacherService;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    // 강사 생성
    @PostMapping("/")
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto requestDto) {
        return teacherService.createTeacher(requestDto);
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

}
