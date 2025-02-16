package com.mycompany.e_commerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.e_commerce.dto.response.product.ProductListResponse;
import com.mycompany.e_commerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-all-products")
    public List<ProductListResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
