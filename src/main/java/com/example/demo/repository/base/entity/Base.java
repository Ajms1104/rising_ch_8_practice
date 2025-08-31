package com.example.demo.repository.base.entity;


import com.example.demo.repository.userproduct.entity.Product;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
//베이스 상품 테이블
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; //상품명
    private Integer price; //상품 가격

    //베이스상품 - 옵션 연결 || 노예(부모) 주인(자식)
    @OneToMany(mappedBy = "base")
    private List<BOAllocated> boallocatedlist = new ArrayList<>();

    @OneToMany(mappedBy = "base")
    private List<Product> products = new ArrayList<>();

    public static Base creat (String name, Integer price){
        return new Base(
            null,
            name,
            price,
            new ArrayList<>(),
            new ArrayList<>()
        );
    }

}
