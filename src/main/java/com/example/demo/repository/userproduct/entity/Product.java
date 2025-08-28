package com.example.demo.repository.userproduct.entity;


import com.example.demo.repository.base.entity.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
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

//상품테이블
public class Product {
    //PK ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; //상품명
    private String comment; //상품 설명

    private LocalDate created_at; //생성일
    private LocalDate updated_at; //수정일
    private LocalDate deleted_at; //삭제일

    @ManyToOne(fetch = FetchType.LAZY)
    //상품 & 유저 //주인(자식), 노예(부모)
    @JoinColumn(name = "user_id") //노란줄 뜨는 이유 : 아직 DB에 테이블이 없어서
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    //상품 & 베이스 상품 //주인(자식), 노예(부모)
    @JoinColumn(name = "base_id")
    private Base base;

    @OneToMany(mappedBy = "Product")
    //상품 & 사진 연결 테이블 //노예(부모) , 주인(자식)
    private List<PPAllocated> ppallocated = new ArrayList<>();

    //정적 생성 매서드
    public static Product create(String name, String comment, User user, Base base) {
        return new Product(
            null,
            name,
            comment,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now(),
            user,
            base,
            new ArrayList<>() //사진 연결 테이블
        );
    }


}
