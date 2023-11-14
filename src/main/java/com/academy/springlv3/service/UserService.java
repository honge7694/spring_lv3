package com.academy.springlv3.service;

import com.academy.springlv3.dto.user.SignupRequestDto;
import com.academy.springlv3.entity.User;
import com.academy.springlv3.entity.UserRoleEnum;
import com.academy.springlv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity signup(SignupRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword()); // TODO: PASSWORD 알파벳 대소문자 숫자 특수문자 구성

        // Email Validation
        String email = requestDto.getEmail(); // TODO: 이메일 형식

        // 부서 별 권한 설정
        String part = requestDto.getPart();
        UserRoleEnum auth = UserRoleEnum.MANAGE;
        if (StringUtils.equals(part, "마케팅")) {
            auth = UserRoleEnum.STAFF;
        }
        
        User user = new User(email, password, part, auth);
        System.out.println("user = " + user.getEmail() + user.getPassword() + user.getPart() + user.getAuth());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("회원가입을 환영합니다.");
    }
}
