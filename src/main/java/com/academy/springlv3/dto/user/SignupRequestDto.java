package com.academy.springlv3.dto.user;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String email;
    private String password;
    private String part;
}
