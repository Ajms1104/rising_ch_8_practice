package com.example.demo.controller.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto { //유저 회원가입 요청 Dto
    //최소한 필요한 것
    //아이디
    @NotNull
    @Size(min = 5, max=10, message = "아이디는 5자 이상 10자 이하입니다. ")
    private String id;

    //비밀번호
    @NotNull
    //Pattern 부여하기 / Gemini
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$",
        message = "비밀번호는 최소 8자 이상이어야 하며, 대문자와 소문자를 각각 최소 1개 포함해야 합니다.")
    private String password;

    //이름
    private String name;

    //연락처들
    private String phone;
    private String email;
}
