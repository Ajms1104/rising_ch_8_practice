package com.example.demo.controller.dto.Product;

import com.example.demo.repository.userproduct.entity.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCreatResponseDto { //상품 생성 + 사진 업로드 | 반환
    //반환할 때 필요한 것
    private Integer productid; //상품 ID
    private String productname; //상품명 ex. 고양이 머그컵`
    private String basename; //베이스 상품명 ex. 머그컵
    private List<String> photourl; //사진 URL

    public static ProductCreatResponseDto from(Product product, List<String> photourl) {
        return new ProductCreatResponseDto(
            product.getId(),
            product.getName(),
            //Json 한 번 생각할 것
            product.getBase().getName(),
            photourl
        );

//        상품 ID : 123
//        상품명 : 고양이 머그컵
//        베이스상품 : 머그컵
//        이미지 URL : [
//            ./파일경로 1
//            ./ 파일경로 2
//        ]
    }

}
