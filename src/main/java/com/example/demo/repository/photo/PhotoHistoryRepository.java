package com.example.demo.repository.photo;

import com.example.demo.repository.photo.entity.PhotoHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoHistoryRepository extends JpaRepository<PhotoHistory, Integer> {//ID, PK

}
