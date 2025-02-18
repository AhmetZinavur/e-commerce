package com.mycompany.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.e_commerce.dto.request.admin.AdminRegisterRequest;
import com.mycompany.e_commerce.dto.request.admin.AdminUpdateRequest;
import com.mycompany.e_commerce.dto.request.customer.CustomerRegisterRequest;
import com.mycompany.e_commerce.dto.request.customer.CustomerUpdateRequest;
import com.mycompany.e_commerce.dto.request.storeowner.StoreOwnerRegisterRequest;
import com.mycompany.e_commerce.dto.request.user.LoginRequest;
import com.mycompany.e_commerce.dto.response.admin.AdminCreateResponse;
import com.mycompany.e_commerce.dto.response.customer.CustomerCreateResponse;
import com.mycompany.e_commerce.dto.response.storeowner.StoreOwnerCreateResponse;
import com.mycompany.e_commerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-customer")
    public ResponseEntity<CustomerCreateResponse> createCustomer(@RequestBody CustomerRegisterRequest customer) {
        return ResponseEntity.ok(userService.createCustomer(customer));
    }

    @PostMapping("/create-admin")
    public ResponseEntity<AdminCreateResponse> createAdmin(@RequestBody AdminRegisterRequest admin) {
        return ResponseEntity.ok(userService.createAdmin(admin));
    }

    @PostMapping("/create-store-owner")
    public ResponseEntity<StoreOwnerCreateResponse> createStoreOwner(@RequestBody StoreOwnerRegisterRequest storeOwner) {
        return ResponseEntity.ok(userService.createStoreOwner(storeOwner));
    }

    @PutMapping("/update-customer")
    public void updateCustomer(String token, @RequestBody CustomerUpdateRequest customer) {
        userService.updateCustomer(token, customer);
    }

    @PutMapping("/update-admin")
    public void updateAdmin(String token, @RequestBody AdminUpdateRequest admin) {
        userService.updateAdmin(token, admin);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }   

    @DeleteMapping("/delete-admin")
    public void deleteAdmin(String token, Long id) {
        userService.deleteAdmin(token, id);
    }
}
