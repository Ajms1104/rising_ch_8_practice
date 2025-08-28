package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserCreateResponseDto;
import com.example.demo.repository.userproduct.UserRepository;
import com.example.demo.repository.userproduct.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service  // 존재 자체의 문제는 Service
@RequiredArgsConstructor

public class UserService { //유저 관련 총 서비스
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 그 중에서 유저 회원가입 서비스..
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        // 아이디가 중복 되는지
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new IllegalArgumentException("이미 다른 유저가 사용중인 ID 입니다");
        }
        //Dto & Entity 매핑
        User newUser = User.create(
            requestDto.getUserId(),
            passwordEncoder.encode(requestDto.getPassword()) //암호화된 비밀번호
        );

        //DB 저장
        User savedUser = userRepository.save(newUser);

        //Entity -> Dto로 변환
        return UserCreateResponseDto.from(savedUser);
    }
}
