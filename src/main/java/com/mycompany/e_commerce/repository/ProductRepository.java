package com.mycompany.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.e_commerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
