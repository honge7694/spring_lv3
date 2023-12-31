package com.academy.springlv3.dto.lecture;

import com.academy.springlv3.entity.Lecture;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private Long teacherId;
    private String name;
    private int price;
    private String introduce;
    private String category;
    private LocalDateTime createdAt;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getId();
        this.teacherId = lecture.getTeacherId();
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.introduce = lecture.getIntroduce();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
    }
}
