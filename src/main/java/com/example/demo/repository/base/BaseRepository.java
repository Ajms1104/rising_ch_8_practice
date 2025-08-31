package com.example.demo.repository.base;

import com.example.demo.repository.base.entity.Base;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<Base, String> {
    Optional<Base> findByName(String name);

}
