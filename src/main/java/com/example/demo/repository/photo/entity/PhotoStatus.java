package com.example.demo.repository.photo.entity;


//사진상태 관련 Enum
//Entity | Photo, PhotoHistory 에서 사용 중
public enum PhotoStatus {
    Registered, //등록
    Approved, //승인
    Rejection, //거절
    Ban //금지
}
