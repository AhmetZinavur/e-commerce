package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.dto.request.order.CreateOrderRequest;
import com.mycompany.e_commerce.entity.Order;
import com.mycompany.e_commerce.entity.OrderDetail;
import com.mycompany.e_commerce.entity.Product;
import com.mycompany.e_commerce.entity.Store;
import com.mycompany.e_commerce.entity.User;
import com.mycompany.e_commerce.entity.enums.OrderStatus;
import com.mycompany.e_commerce.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;

    @Transactional
    protected void createOrder(User user, CreateOrderRequest createOrderRequest) {
        Product product = productService.getProductById(createOrderRequest.getProductId());
        Store store = Store.builder().id(createOrderRequest.getStoreId()).build();
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.WAIT.toString())
                .createAt(LocalDateTime.now().toString())
                .total(product.getPrice() * createOrderRequest.getQuantity())
                .store(store)
                .build();
        orderRepository.save(order);
        orderDetailService.createOrderDetail(
                OrderDetail.builder()
                .order(order)
                .product(product)
                .unitPrice(product.getPrice())
                .store(store)
                .quantity(createOrderRequest.getQuantity())
                .createAt(LocalDateTime.now().toString())
                .build());
    }

    protected void approveOrder(Long orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(OrderStatus.APPROVED.toString());
            order.setUpdateAt(LocalDateTime.now().toString());
            orderRepository.save(order);
            updateProductStock(orderId);
        });
    }

    private void updateProductStock(Long orderId) {
        OrderDetail orderDetail = orderDetailService.findByOrderId(orderId);
        Product product = productService.getProductById(orderDetail.getProduct().getId());
        product.setStock(product.getStock() - orderDetail.getQuantity());
        productService.updateProduct(product);
    }

    protected Double getTotalSalesForToday(Long storeId) {
        return orderRepository.getTotalSalesForToday(storeId);
    }

    protected Double getTotalSalesForAllStoresForToday(String token) {
        return orderRepository.getTotalSalesForAllStoresForToday();
    }
}