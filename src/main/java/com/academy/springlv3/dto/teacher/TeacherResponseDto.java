package com.academy.springlv3.dto.teacher;

import com.academy.springlv3.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponseDto {
    private Long id;
    private String name;
    private int career;
    private String company;
    private String phoneNumber;
    private String introduce;

    public TeacherResponseDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduce = teacher.getIntroduce();
    }
}
