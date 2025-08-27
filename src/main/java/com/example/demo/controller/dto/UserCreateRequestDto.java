package com.example.demo.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class UserCreateRequestDto { //유저 회원가입 요청 Dto
    //최소한 필요한 것
    //아이디
    @NotNull
    @Size(min = 5, max=10)
    private String userId;

    //비밀번호
    @NotNull
    //Pattern 부여하기
    private String password;

    //이름
    private String name;

    //연락처들
    private String phone;
    private String email;
}
