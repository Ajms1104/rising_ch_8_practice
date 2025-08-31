package com.example.demo.controller.dto.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequestDto { //상품 생성 + 사진 업로드 | 요청
    //최소한 필요한 것
    //베이스 제품 이름, 상품 이름, 사진
    @NotNull(message = "베이스 제품 이름은 필수 입니다")
    private String basename;

    @NotNull(message = "상품 이름은 필수 입니다")
    @Size(max = 20, message = "상품 이름은 최대 20자 입니다")
    private String productname;

    private String comment; //상품설명

    @NotNull
    @Size(min=1, max=5, message = "사진은 최소 1장~최대 5장 까지 가능합니다")
    private List<MultipartFile> photo; //★★★
}
