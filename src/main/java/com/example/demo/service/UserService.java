package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserCreateResponseDto;
import com.example.demo.repository.userproduct.UserRepository;
import com.example.demo.repository.userproduct.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service  // 존재 자체의 문제는 Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService { //유저 관련 총 서비스
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void init() {
        if (!userRepository.existsByUsername("TESTtest")) {

            this.createUser(new UserCreateRequestDto("TESTtest", "Testtest11", "Ahjeong","1234-1234","gmail"));

            System.out.println("사용자가 성공적으로 생성되었습니다.");
        }
    }

    // 그 중에서 유저 로그인 서비스 + spring Security에 의해 자동 호출
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username) // DB에서 아이디로 사용자 조회
            .orElseThrow(() -> new UsernameNotFoundException("해당 아이디를 찾을 수 없습니다: " + username));
        //조회된 사용자 정보 -> UserDetails 객체로 변환
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), // 로그인 ID
            user.getPassword(), // 비밀번호
            Collections.emptyList() // 권한 목록 (지금은 없음)
        );
    }

    // 그 중에서 유저 회원가입 서비스
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        // 아이디가 중복 되는지
        if (userRepository.existsByUsername(requestDto.getId())) {
            throw new IllegalArgumentException("이미 다른 유저가 사용중인 ID 입니다");
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        //Dto & Entity 매핑
        User newUser = User.create(
            requestDto.getId(),
            passwordEncoder.encode(requestDto.getPassword()) //암호화된 비밀번호
        );

        //DB 저장
        User savedUser = userRepository.save(newUser);

        //Entity -> Dto로 변환
        return UserCreateResponseDto.from(savedUser);
    }
}
