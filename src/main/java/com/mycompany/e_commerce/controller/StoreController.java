package com.mycompany.e_commerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.e_commerce.dto.request.product.AddNewProductRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductNameRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductPriceRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductStockRequest;
import com.mycompany.e_commerce.dto.request.store.AddNewStoreRequest;
import com.mycompany.e_commerce.entity.Store;
import com.mycompany.e_commerce.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    
    @PostMapping("/create-store")
    public void createStore(String token, AddNewStoreRequest store) {
        storeService.createStore(token, store);
    }

    @PostMapping("/add-product")
    public void addProduct(String token, AddNewProductRequest newProductRequest) {
        storeService.addNewProductToStore(token, newProductRequest);
    }

    @PutMapping("/update-product-name")
    public void updateProductName(String token, UpdateProductNameRequest updateProductNameRequest) {
        storeService.updateProductName(token, updateProductNameRequest);
    }

    @PutMapping("/update-product-price")
    public void updateProductPrice(String token, UpdateProductPriceRequest updateProductPriceRequest) {
        storeService.updateProductPrice(token, updateProductPriceRequest);
    }

    @PutMapping("/update-product-stock")
    public void updateProductStock(String token, UpdateProductStockRequest updateProductStockRequest) {
        storeService.updateProductStock(token, updateProductStockRequest);
    }

    @GetMapping("/get-store-info-for-admin")
    public Store getStoreInfoForAdmin(String token, @RequestParam Long id) {
        return storeService.getStoreInfoForAdmin(token, id);
    }

    @PutMapping("/approve-order")
    public void approveOrder(String token, @RequestParam Long orderId) {
        storeService.approveOrder(token, orderId);
    }    
}
