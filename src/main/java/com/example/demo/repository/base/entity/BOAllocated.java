package com.example.demo.repository.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor

//베이스상품+옵션 연결 테이블
public class BOAllocated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Option_id")
    private BaseOption baseoption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Base_Id")
    private Base base;

    public static BOAllocated creat (BaseOption baseoption, Base base){
        return new BOAllocated(
            null,
            baseoption,
            base
        );
    }


}
