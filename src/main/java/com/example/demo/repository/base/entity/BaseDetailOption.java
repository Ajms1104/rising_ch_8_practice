package com.example.demo.repository.base.entity;


import jakarta.persistence.Entity;
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
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//베이스상품 세부 옵션
public class BaseDetailOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //옵션값 | ex. 빨간색, 주황색, s, m, 도자기, 유리 등등
    private String value;

    @OneToMany(mappedBy = "basedetailoption")
    //세부 옵션 & 옵션 연결 | 노예(부모), 주인(자식)
    private List<BaseOption> baseOptions = new ArrayList<>();

    //정적 생성 매서드
    public static BaseDetailOption create(String value){
        return new BaseDetailOption(
            null,
            value,
            new ArrayList<>()
        );
    }

}
