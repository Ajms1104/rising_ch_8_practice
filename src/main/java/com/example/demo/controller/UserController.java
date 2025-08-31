package com.example.demo.controller;


import com.example.demo.controller.dto.User.UserCreateRequestDto;
import com.example.demo.controller.dto.User.UserCreateResponseDto;
import com.example.demo.controller.dto.User.UserProductResponseDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    //비밀번호 값이나 아이디 값 오류는 컨트롤러

    private final UserService userService;
    private final ProductService productService;

    //회원가입 (API 2번)
    @PostMapping("/signup") //ex. /api/users/signup
    public ResponseEntity<UserCreateResponseDto> signup(
        @Valid //에서 ID/PW 오류 검증
        @RequestBody
        UserCreateRequestDto requestDto
    ){
        //서비스 메서드 호출
        UserCreateResponseDto responseDto = userService.createUser(requestDto);

        //서비스에서 이미 변환된 응답 DTO를 그대로 body에 담아 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //유저가 올린 등록한 상품 API
    @GetMapping("/products")
    public ResponseEntity<List<UserProductResponseDto>> getMyProducts() {
        //서비스 메서드 호출
        List<UserProductResponseDto>Products = productService.findProductsByUser();

        return ResponseEntity.ok(Products);
    }

}