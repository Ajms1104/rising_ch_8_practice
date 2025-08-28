package com.example.demo.repository.photo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity

//사진 변화저장 테이블
public class PhotoHistory {
    //PK ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //과거 사진 상태
    @Enumerated(EnumType.STRING)
    private PhotoStatus oldstatus;

    //현재의 사진 상태
    @Enumerated(EnumType.STRING)
    private PhotoStatus nowstatus;

    private LocalDate created_at; //생성일
    private LocalDate updated_at; //수정일

    @ManyToOne(fetch = FetchType.LAZY)
    //사진 변화저장 & 사진 테이블 //주인(자식) 노예(부모)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public static PhotoHistory create(PhotoStatus oldstatus, PhotoStatus nowstatus, Photo photo) {
        return new PhotoHistory(
            null,
            oldstatus,
            nowstatus,
            LocalDate.now(),
            LocalDate.now(),
            photo
        );
    }
}

