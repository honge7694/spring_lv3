package com.academy.springlv3.service;

import com.academy.springlv3.dto.lecture.LectureResponseDto;
import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import com.academy.springlv3.dto.teacher.TeacherResponseDto;
import com.academy.springlv3.entity.Lecture;
import com.academy.springlv3.entity.Teacher;
import com.academy.springlv3.exception.TeacherNotFoundException;
import com.academy.springlv3.repository.LectureRepository;
import com.academy.springlv3.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {
        // RequestDto -> Entity
        Teacher teacher = new Teacher(requestDto);
        // DB 저장
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }

    public TeacherResponseDto getTeacher(Long id) {
        return new TeacherResponseDto(findTeacher(id));
    }

    @Transactional
    public TeacherResponseDto editTeacher(Long id, TeacherRequestDto requestDto) {
        // 강사 존재 확인
        Teacher teacher = findTeacher(id);

        teacher.update(requestDto);
        return new TeacherResponseDto(teacher);
    }

    public List<LectureResponseDto> getTeacherLectures(Long id) {
        List<Lecture> lectures = lectureRepository.findLecturesByTeacherId(id);
        return lectures.stream().map(LectureResponseDto::new).toList();
    }

    public ResponseEntity deleteTeacher(Long id) {
        // 강사 존재 확인
        Teacher teacher = findTeacher(id);

        // teacher 삭제
        teacherRepository.delete(teacher);

        return ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.");
    }

    private Teacher findTeacher(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("존재하지 않는 강사입니다."));
    }
}
