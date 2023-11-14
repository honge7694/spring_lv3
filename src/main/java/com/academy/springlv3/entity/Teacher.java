package com.academy.springlv3.entity;

import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int career;
    private String company;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String introduce;

    public Teacher(TeacherRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduce = requestDto.getIntroduce();
    }

    public void update(TeacherRequestDto requestDto) {
        if (!StringUtils.equals(requestDto.getName(), "")) {
            this.name = requestDto.getName();
        }

        this.career = requestDto.getCareer();

        if (!StringUtils.equals(requestDto.getCompany(), "")) {
            this.company = requestDto.getCompany();
        }
        if (!StringUtils.equals(requestDto.getPhoneNumber(), "")) {
            this.phoneNumber = requestDto.getPhoneNumber();
        }
        if (!StringUtils.equals(requestDto.getIntroduce(), "")) {
            this.introduce = requestDto.getIntroduce();
        }
    }
}
