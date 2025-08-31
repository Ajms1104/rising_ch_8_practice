package com.example.demo.repository.photo;

import com.example.demo.repository.userproduct.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class PhotoRepository extends JpaRepository<User, String>  {

}
