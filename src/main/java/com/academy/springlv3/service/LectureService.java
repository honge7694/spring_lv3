package com.academy.springlv3.service;

import com.academy.springlv3.dto.user.lecture.LectureResponseDto;
import com.academy.springlv3.entity.Lecture;
import com.academy.springlv3.dto.user.lecture.LectureRequestDto;
import com.academy.springlv3.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return new LectureResponseDto(lectureRepository.findLectureByLectureId(lectureId));
    }
}
