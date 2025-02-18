package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.dto.request.product.AddNewProductRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductNameRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductPriceRequest;
import com.mycompany.e_commerce.dto.request.product.UpdateProductStockRequest;
import com.mycompany.e_commerce.dto.request.store.AddNewStoreRequest;
import com.mycompany.e_commerce.dto.request.admin.AddNewStoreAdminRequest;
import com.mycompany.e_commerce.entity.Product;
import com.mycompany.e_commerce.entity.Store;
import com.mycompany.e_commerce.entity.User;
import com.mycompany.e_commerce.entity.enums.Role;
import com.mycompany.e_commerce.exception.customexception.CustomeException;
import com.mycompany.e_commerce.exception.customexception.StoreNotFondException;
import com.mycompany.e_commerce.exception.customexception.UnauthorizedAccessException;
import com.mycompany.e_commerce.repository.StoreRepository;
import com.mycompany.e_commerce.security.JWTManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ProductService productService;
    private final UserService userService;
    private final JWTManager jwtManager;

    @Transactional
    public void createStore(String token, AddNewStoreRequest store) {
        storeRepository.save(Store.builder()
                .name(store.getName())
                .user(userService.getUserById(jwtManager.getUserIdFromToken(token)))
                .createAt(LocalDateTime.now().toString())
                .build());
    }

    @Transactional
    public void createStoreForAdmin(String token, AddNewStoreAdminRequest store) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        User storeOwner = userService.getUserById(store.getStoreOwnerId());
        if (user.getRole().equals(Role.ADMIN.toString())) {
            storeRepository.save(Store.builder()
                    .name(store.getName())
                    .user(storeOwner)
                    .createAt(LocalDateTime.now().toString())
                    .build());
        }
    }

    @Transactional
    public void deleteStoreForAdmin(String token, Long storeId) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            storeRepository.deleteById(storeId);
        } else {
            // return error
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }

    protected Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFondException(CustomeException.STORE_NOT_FOUND));
    }

    @Transactional
    public void addNewProductToStore(String token, AddNewProductRequest newProductRequest) {
        Store store = storeRepository.findByUserId(jwtManager.getUserIdFromToken(token));
        if (store != null) {
            store.getProducts().add(productService.createProduct(Product.builder().name(newProductRequest.getName())
                    .price(newProductRequest.getPrice()).stock(newProductRequest.getStock()).store(store)
                    .createAt(LocalDateTime.now().toString()).build()));
            storeRepository.save(store);
        }
    }

    @Transactional
    public void updateProductName(String token, UpdateProductNameRequest updateProductNameRequest) {
        Store store = storeRepository.findByUserId(jwtManager.getUserIdFromToken(token));
        if (store != null) {
            Product currentProduct = productService.getProductById(updateProductNameRequest.getId());
            currentProduct.setName(updateProductNameRequest.getName());
            currentProduct.setUpdateAt(LocalDateTime.now().toString());
            storeRepository.save(store);
        }
    }

    @Transactional
    public void updateProductStock(String token, UpdateProductStockRequest updateProductStockRequest) {
        Store store = storeRepository.findByUserId(jwtManager.getUserIdFromToken(token));
        if (store != null) {
            Product currentProduct = productService.getProductById(updateProductStockRequest.getId());
            currentProduct.setStock(updateProductStockRequest.getStock());
            productService.updateProduct(currentProduct);
            storeRepository.save(store);
        }
    }

    @Transactional
    public void updateProductPrice(String token, UpdateProductPriceRequest updateProductPriceRequest) {
        Store store = storeRepository.findByUserId(jwtManager.getUserIdFromToken(token));
        if (store != null) {
            Product currentProduct = productService.getProductById(updateProductPriceRequest.getId());
            currentProduct.setPrice(updateProductPriceRequest.getPrice());
            productService.updateProduct(currentProduct);
            storeRepository.save(store);
        } else {
            // return error
            throw new StoreNotFondException(CustomeException.STORE_NOT_FOUND);
        }
    }
}