package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.entity.Product;
import com.mycompany.e_commerce.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public void updateProduct(Product product) {
        product.setUpdateAt(LocalDateTime.now().toString());
        productRepository.save(product);
    }
}
