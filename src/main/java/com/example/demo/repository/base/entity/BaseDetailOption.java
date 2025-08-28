package com.example.demo.repository.base.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity

//베이스상품 세부 옵션
public class BaseDetailOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //옵션값 | ex. 빨간색, 주황색, s, m, 도자기, 유리 등등
    private String value;

    //

    //정적 생성 매서드
    public static BaseDetailOption create(String value){
        return new BaseDetailOption(
            null,
            value
        );
    }

}
