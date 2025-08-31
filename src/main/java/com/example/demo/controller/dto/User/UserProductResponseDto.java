package com.example.demo.controller.dto.User;

import com.example.demo.controller.dto.Photo.PhotoHistoryDto;
import com.example.demo.repository.userproduct.entity.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProductResponseDto { //유저가 등록한 상품 조회 API
    //반환할 것
    private Integer productId; //상품 ID
    private String productName; //상품명
    private String baseName; //베이스상품명

    //조건 | 각 상품마다 내부 리뷰과정 히스토리 과정을 모두 배열로 함께 반환해야한다.
    private List<PhotoHistoryDto> photoHistories;

    public static UserProductResponseDto from(Product entity){
        return new UserProductResponseDto(
            entity.getId(),
            entity.getName(),
            entity.getBase().getName(),

            //Product -> PPAllocated -> Photo -> PhotoHistory 순으로 DTO 생성
            //JSON 생각하며 작성할 것 | + Hotel simple 조회 Dto와 비교하며 생각해볼 것
            entity.getPpallocated().stream()
                //평탄화 -> map -> collect
                .flatMap(pp -> pp.getPhoto().getPhotoHistory().stream())
                .map(PhotoHistoryDto::from)
                .collect(Collectors.toList())
        );
    }

}
