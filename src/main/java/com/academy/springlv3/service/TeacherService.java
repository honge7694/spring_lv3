package com.academy.springlv3.service;

import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import com.academy.springlv3.dto.teacher.TeacherResponseDto;
import com.academy.springlv3.entity.Teacher;
import com.academy.springlv3.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {
        // RequestDto -> Entity
        Teacher teacher = new Teacher(requestDto);
        // DB 저장
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }
}
