package com.academy.springlv3.security;

import com.academy.springlv3.dto.user.LoginRequestDto;
import com.academy.springlv3.entity.UserRoleEnum;
import com.academy.springlv3.jwt.JwtUtil;
import com.academy.springlv3.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            log.info("JWT 생성 ?");

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        try {
            String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
            UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getAuth();

            String token = jwtUtil.createToken(username, role);
            log.info("[JwtAuthenticationFilter] token : " + token);

            // 응답 헤더에 토큰 추가
            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

            // 응답 상태 코드 및 메시지 설정
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("로그인에 성공하였습니다.");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("IOException 발생 : " + e.getMessage());
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        try {
//        response.setStatus(401);
        // 응답 상태 코드 및 메시지 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("이메일 및 패스워드를 확인해주세요.");
        response.getWriter().flush();
        response.getWriter().close();
        } catch (IOException e) {
            log.error("IOException 발생 : " + e.getMessage());
        }
    }

}
