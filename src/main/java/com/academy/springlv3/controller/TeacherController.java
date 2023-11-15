package com.academy.springlv3.controller;

import com.academy.springlv3.dto.lecture.LectureResponseDto;
import com.academy.springlv3.dto.teacher.TeacherRequestDto;
import com.academy.springlv3.dto.teacher.TeacherResponseDto;
import com.academy.springlv3.entity.UserRoleEnum;
import com.academy.springlv3.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
@Tag(name = "강사", description = "강사 API")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/")
    @Operation(summary = "강사 생성", description = "강사를 생성하고 강사 정보를 반환 받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "관리자만 접근 가능합니다."),
    })
    public TeacherResponseDto createTeacher(
            @RequestBody TeacherRequestDto requestDto) {
        return teacherService.createTeacher(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "강사 조회", description = "강사의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TeacherResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "강사를 찾을 수 없습니다."),
    })
    public TeacherResponseDto getTeacher(
            @PathVariable
            @Positive(message = "강사 id는 양수입니다.")
            @Schema(description = "강사 id", example = "1")
            Long id) {
        return teacherService.getTeacher(id);
    }

    @Secured(UserRoleEnum.Authority.MANAGER) // 매니저만 접근
    @PutMapping("/{id}")
    @Operation(summary = "강사 정보 수정", description = "강사의 정보를 수정합니다. 매니저만 접근 가능합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(schema = @Schema(implementation = TeacherResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "강사를 찾을 수 없습니다."),
    })
    public TeacherResponseDto editTeacher(
            @PathVariable
            @Positive(message = "강사 id는 양수입니다.")
            @Schema(description = "강사 id", example = "1")
            Long id,
            @RequestBody TeacherRequestDto requestDto) {
        return teacherService.editTeacher(id, requestDto);
    }

    @GetMapping("/{id}/lectures")
    @Operation(summary = "강사의 강의 리스트 조회", description = "강사가 강의하는 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LectureResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "강의가 없습니다."),
    })
    public List<LectureResponseDto> getTeacherLectures(
            @PathVariable
            @Positive(message = "강사 id는 양수입니다.")
            @Schema(description = "강사 id", example = "1")
            Long id) {
        return teacherService.getTeacherLectures(id);
    }

    @Secured(UserRoleEnum.Authority.MANAGER) // 매니저만 접근
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "404", description = "강사가 없습니다."),
    })
    public ResponseEntity deleteTeacher(
            @PathVariable
            @Positive(message = "강사 id는 양수입니다.")
            @Schema(description = "강사 id", example = "1")
            Long id) {
        return teacherService.deleteTeacher(id);
    }

}
