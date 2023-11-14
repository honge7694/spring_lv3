package com.academy.springlv3.service;

import com.academy.springlv3.dto.user.lecture.LectureResponseDto;
import com.academy.springlv3.entity.Lecture;
import com.academy.springlv3.dto.user.lecture.LectureRequestDto;
import com.academy.springlv3.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic="lecture service")
@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    public  LectureResponseDto registerBook(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture(requestDto);
        return new LectureResponseDto(lectureRepository.save(lecture));
    }


    public LectureResponseDto findLecture(Long lectureId) {
        return new LectureResponseDto(lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId));
    }

    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {
        Lecture lecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId);
        if(lecture == null){
            throw new EntityNotFoundException("해당 강의를 찾지 못했습니다.");
        }
        lecture.update(requestDto);
        return new LectureResponseDto(lectureRepository.save(lecture));
    }

    public List<LectureResponseDto> searchByCategory(String category){
        List<Lecture> lectures = lectureRepository.findLecturesByCategoryOrderByCreatedAt(category);
        if(lectures == null){
            throw new EntityNotFoundException("해당 카테고리로 조회가 되지 않습니다.");
        }
        return lectures.stream().map(LectureResponseDto::new).toList();
    }
}
