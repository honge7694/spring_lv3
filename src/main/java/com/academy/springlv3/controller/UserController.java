package com.academy.springlv3.controller;

import com.academy.springlv3.dto.user.SignupRequestDto;
import com.academy.springlv3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Fields Validation
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                    .toList();//collect(Collectors.toList());

            // JSON 형태로 에러 메시지를 가공하여 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return userService.signup(requestDto);
    }
}
