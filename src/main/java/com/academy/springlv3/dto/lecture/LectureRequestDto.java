package com.academy.springlv3.dto.lecture;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class LectureRequestDto {

    @NotBlank(message = "강의 이름을 입력하세요.")
    private String name;

    @NotBlank(message = "강의 가격을 입력하세요.")
    private int price;

    private String introduce;

    @NotBlank(message = "카테고리를 입력하세요.")
    private String category;

    private Long teacherId;

}
