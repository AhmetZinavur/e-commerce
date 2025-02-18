package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.dto.response.product.ProductListResponse;
import com.mycompany.e_commerce.entity.Product;
import com.mycompany.e_commerce.exception.customexception.CustomeException;
import com.mycompany.e_commerce.exception.customexception.ProductNotFoundException;
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
        return productRepository.findById(id).orElseThrow( 
            () -> new ProductNotFoundException(CustomeException.PRODUCT_NOT_FOUND)
        );
    }

    public List<ProductListResponse> getAllProducts() {
        return productRepository.findAll().stream()
        .map(product -> ProductListResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .stock(product.getStock())
            .storeName(product.getStore().getName())
            .build())
        .collect(Collectors.toList());
    }

    @Transactional
    public void updateProduct(Product product) {
        product.setUpdateAt(LocalDateTime.now().toString());
        productRepository.save(product);
    }
}
