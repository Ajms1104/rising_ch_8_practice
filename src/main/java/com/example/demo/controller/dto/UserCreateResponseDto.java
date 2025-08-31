package com.example.demo.controller.dto;

import com.example.demo.repository.userproduct.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor

public class UserCreateResponseDto {
    //유저 회원가입 반환 dto

    private String username ;
    //반환할 때는 비밀번호 주면 안된다..
    private String name;
    private String phone;
    private String email;

    public static UserCreateResponseDto from(User entity){
        return new UserCreateResponseDto(
            entity.getUsername(),
            entity.getName(),
            entity.getPhone(),
            entity.getEmail()
        );
    }


}
