package com.example.demo.controller;


import com.example.demo.controller.dto.Product.ProductCreateRequestDto;
import com.example.demo.controller.dto.Product.ProductCreatResponseDto;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor

public class ProductController {
    //상품명, 베이스상품명, 사진 오류는 컨트롤러

    private final ProductService productService;

    //상품 생성 + 이미지
    @PostMapping("")
    public ResponseEntity<ProductCreatResponseDto> createProduct(
        @Valid //상품명, 베이스상품명, 사진 오류값에 대한 검증
        @RequestBody
        ProductCreateRequestDto requestDto
    ){
       //서비스 메서드 호출
       ProductCreatResponseDto responseDto = productService.createProduct(requestDto);

       //서비스에서 이미 변환된 응답 DTO를 그대로 Body에 담아 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
