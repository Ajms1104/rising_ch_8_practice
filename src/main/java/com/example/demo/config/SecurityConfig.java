package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //설정파일
public class SecurityConfig {
    @Bean //비밀번호 관련 객체
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //* BCryptPasswordEncoder : 스프링 시큐리티에서 제공하는 단방향 암호화 도구
    }
}
