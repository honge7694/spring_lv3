package com.academy.springlv3.controller;

import com.academy.springlv3.dto.user.lecture.LectureRequestDto;
import com.academy.springlv3.dto.user.lecture.LectureResponseDto;
import com.academy.springlv3.service.LectureService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lecture")
public class LectureController {
    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/register")
    public ResponseEntity<LectureResponseDto> register(@RequestBody LectureRequestDto requestDto){
        return new ResponseEntity<>(lectureService.registerBook(requestDto), HttpStatus.OK);

    }

    @GetMapping("/{lecture_id}")
    public ResponseEntity<LectureResponseDto> lectureListOfTeacher(@PathVariable Long lecture_id){
        return new ResponseEntity<>(lectureService.findLecture(lecture_id), HttpStatus.OK);
    }


}
