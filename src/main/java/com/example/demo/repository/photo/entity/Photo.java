package com.example.demo.repository.photo.entity;

import com.example.demo.repository.userproduct.entity.PPAllocated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
//사진테이블
public class Photo {
    //PK ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; //사진명
    private String comment; //사진 설명
    private String url; //사진 URL

    //사진 상태 enum (등록, 승인, 거절, 금지)
    //enum 코드 도움 : Gemini
    @Enumerated(EnumType.STRING)
    private PhotoStatus status;

    @OneToMany(mappedBy = "photo")
    //사진 & 사진 변화저장 테이블 // 노예(부모), 주인(자식)
    private List<PhotoHistory> photoHistory = new ArrayList<>();

    @OneToMany(mappedBy = "photo")
    //사진 & 상품 연결 테이블 //노예(부모), 주인(자식)
    private List<PPAllocated> product = new ArrayList<>();

    //정적 생성 메서드
    public static Photo create(String name, String comment, String url, PhotoStatus status ) {
        return new Photo(
            null,
            name,
            comment,
            url,
            status, //사진 상태
            new ArrayList<>(), // 사진-사진변화저장 테이블
            new ArrayList<>() //사진-상품 연결테이블
        );
    }


}
