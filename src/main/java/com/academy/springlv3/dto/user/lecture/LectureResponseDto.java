package com.academy.springlv3.dto.user.lecture;

import com.academy.springlv3.entity.Lecture;
import lombok.Data;
import lombok.Getter;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String name;
    private int price;
    private String introduce;
    private String category;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.introduce = lecture.getIntroduce();
        this.category = lecture.getCategory();
    }
}
