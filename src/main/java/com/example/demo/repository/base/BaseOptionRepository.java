package com.example.demo.repository.base;

import com.example.demo.repository.base.entity.BaseOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseOptionRepository extends JpaRepository<BaseOption, Integer> { //ID, PK

}
