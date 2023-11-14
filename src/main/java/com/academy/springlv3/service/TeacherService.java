package com.academy.springlv3.service;

import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import com.academy.springlv3.dto.teacher.TeacherResponseDto;
import com.academy.springlv3.entity.Teacher;
import com.academy.springlv3.exception.TeacherNotFoundException;
import com.academy.springlv3.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public TeacherResponseDto getTeacher(Long id) {
        return new TeacherResponseDto(teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("존재하지 않는 강사입니다."))
        );
    }
}
