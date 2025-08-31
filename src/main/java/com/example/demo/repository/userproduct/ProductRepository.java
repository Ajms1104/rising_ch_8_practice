package com.example.demo.repository.userproduct;

import com.example.demo.repository.userproduct.entity.Product;
import com.example.demo.repository.userproduct.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByUser(User user);

}
