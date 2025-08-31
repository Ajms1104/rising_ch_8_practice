package com.example.demo.repository.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

//베이스상품 옵션 테이블
public class BaseOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //옵션명 | ex. 색깔, 재질, 사이즈, 형태 등등
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    //옵션 & 세부옵션 연결 | 주인(자식) & 노예(부모)
    @JoinColumn(name = "DetailOption_Id")
    private BaseDetailOption basedetailoption;

    @OneToMany(mappedBy = "baseoption")
    private List<BOAllocated> boallocateds = new ArrayList<>();

    //정적 생성 매서드
    public static BaseOption create(String name, BaseDetailOption basedetailoption){
        return new BaseOption(
            null,
            name,
            basedetailoption,
            new ArrayList<>()
        );
    }






}
