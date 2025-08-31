package com.example.demo.repository.userproduct;

import com.example.demo.repository.userproduct.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //아이디로 사용자가 있는지 없는지 구별
    boolean existsByUsername(String username);

}
