package com.example.demo.repository.base;

import com.example.demo.repository.base.entity.BaseDetailOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseDetailRepository extends JpaRepository<BaseDetailOption, Integer> {  //ID = PK

}
