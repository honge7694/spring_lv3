package com.academy.springlv3.dto.teacher;

import com.academy.springlv3.entity.Teacher;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class TeacherRequestDto {
    private String name;
    private int career;
    private String company;
    private String phoneNumber;
    private String introduce;

}
