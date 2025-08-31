package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정파일
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    //보안공부가 더 필요하긴 하겠다..

    @Bean //비밀번호 관련 객체
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //* BCryptPasswordEncoder : 스프링 시큐리티에서 제공하는 단방향 암호화 도구
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //여기부터
        //CSRF 보호기능 비활성화
        http.csrf(csrf -> csrf.disable());
        // 1. (가장 중요) 모든 요청에 대한 접근 권한 설정
        http.authorizeHttpRequests(auth -> auth
            // 아래의 경로들은 인증 없이 누구나 접근 가능하도록 허용
            .requestMatchers("/api/users/**", "/signup", "/error").permitAll()
            // 그 외의 모든 요청은 반드시 인증(로그인)을 거쳐야 함
            .anyRequest().authenticated()
        );
        http.httpBasic(withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //여기까지 코드는 제미나이 2.5 || 그외는 노션에 과제로 올라왔던 부분
        return http.build();
    }
}
