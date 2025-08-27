package com.example.demo.service;

import com.example.demo.controller.dto.UserCreateRequestDto;
import com.example.demo.controller.dto.UserCreateResponseDto;
import com.example.demo.repository.userproduct.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Setter

public class UserService { //유저 관련 총 서비스
    private final UserRepository userRepository;

    //비밀번호가 없거나 아이디가 없을 경우 = 존재자체의 문제는 Service
    // 그 중에서 유저 회원가입 서비스..
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto){
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new IllegalArgumentException("이미 다른 유저가 사용중인 ID 입니다");
        }
}
