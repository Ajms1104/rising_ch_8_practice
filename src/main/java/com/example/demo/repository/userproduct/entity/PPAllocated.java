package com.example.demo.repository.userproduct.entity;

import com.example.demo.repository.photo.entity.Photo;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity

//상품과 사진 연결 테이블
public class PPAllocated {
    //PK ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    //연결 테이블과 상품// 주인(자식), 노예(부모)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    //연결 테이블과 사진 //주인(자식), 노예(부모)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    private LocalDate created_at; //생성일
    private LocalDate updated_at; //수정일
    private LocalDate deleted_at; //삭제일

    private static PPAllocated create(Product product, Photo photo){
        return new PPAllocated(
            null,
            product,
            photo,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now()
        );
    }
}
