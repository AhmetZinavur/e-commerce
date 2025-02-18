package com.mycompany.e_commerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.e_commerce.dto.request.order.CreateOrderRequest;
import com.mycompany.e_commerce.dto.response.product.ProductSalesResponse;
import com.mycompany.e_commerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PutMapping("/approve-order")
    public void approveOrder(String token, Long id) {
        orderService.approveOrder(token, id);
    }

    @PostMapping("/create-order")
    public void createOrder(String token, @RequestBody CreateOrderRequest order) {
        orderService.createOrder(token, order);
    }

    @GetMapping("/get-total-sales-for-today-for-admin")
    public Double getTotalSalesForToday(String token, Long storeId) {
        return orderService.getTotalSalesForToday(token, storeId);
    }

    @GetMapping("/get-total-sales-for-all-stores-for-today-for-admin")
    public Double getTotalSalesForAllStoresForToday(String token) {
        return orderService.getTotalSalesForAllStoresForToday(token);
    }

    @GetMapping("/get-store-monthly-sales-info")
    public List<ProductSalesResponse> getStoreMonthlySalesInfo(String token, int month, int year, Long storeId) {
        return orderService.getStoreMonthlySalesInfo(token, month, year, storeId);
    }
}
