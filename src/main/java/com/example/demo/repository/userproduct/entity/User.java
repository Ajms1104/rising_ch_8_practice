package com.example.demo.repository.userproduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

//유저테이블
public class User {
    //PK ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB의 자동생성..?
    private Integer userId;

    //로그인 ID
    @Column(nullable = false, unique = true, length = 10)
    private String id;

    //로그인 PW
    @Column(nullable = false)
    private String password;

    private String name;
    private String email;
    private String phone;

    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;


    @OneToMany(mappedBy = "user")
    //유저 & 상품 //노예(부모), 주인(자식)
    private List<Product> products = new ArrayList<>();

    //정적 생성 매서드
    public static User create(String id, String password) {
        return new User(
            null,
            id,
            password,
            null,
            null,
            null,
            LocalDate.now(),
            LocalDate.now(),
            LocalDate.now(),
            new ArrayList<>()
        );
    }
    //뭔가 null이 많으니 보기가 좀 그런데..🤨
    //builder 로 고쳐한다는데.. 음..
}
