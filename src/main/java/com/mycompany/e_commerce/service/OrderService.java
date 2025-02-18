package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.dto.request.order.CreateOrderRequest;
import com.mycompany.e_commerce.dto.response.product.ProductSalesResponse;
import com.mycompany.e_commerce.entity.Order;
import com.mycompany.e_commerce.entity.OrderDetail;
import com.mycompany.e_commerce.entity.Product;
import com.mycompany.e_commerce.entity.Store;
import com.mycompany.e_commerce.entity.User;
import com.mycompany.e_commerce.entity.enums.OrderStatus;
import com.mycompany.e_commerce.entity.enums.Role;
import com.mycompany.e_commerce.exception.customexception.CustomeException;
import com.mycompany.e_commerce.exception.customexception.InsuficientStockException;
import com.mycompany.e_commerce.exception.customexception.UnauthorizedAccessException;
import com.mycompany.e_commerce.repository.OrderRepository;
import com.mycompany.e_commerce.security.JWTManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final ProductService productService;
    private final UserService userService;
    private final JWTManager jwtManager;

    @Transactional
    public void createOrder(String token, CreateOrderRequest createOrderRequest) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        Product product = productService.getProductById(createOrderRequest.getProductId());
        Store store = Store.builder().id(createOrderRequest.getStoreId()).build();
        if (createOrderRequest.getQuantity() > product.getStock()) {
            throw new InsuficientStockException(CustomeException.INSUFICIENT_STOCK);
        }
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

    @Transactional
    public void approveOrder(String token, Long orderId) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        if (user.getRole().equals(Role.SELLER.toString())) {
            orderRepository.findById(orderId).ifPresent(order -> {
                order.setStatus(OrderStatus.APPROVED.toString());
                order.setUpdateAt(LocalDateTime.now().toString());
                orderRepository.save(order);
                updateProductStock(orderId);
            });
        } else {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }

    private void updateProductStock(Long orderId) {
        OrderDetail orderDetail = orderDetailService.findByOrderId(orderId);
        Product product = productService.getProductById(orderDetail.getProduct().getId());
        product.setStock(product.getStock() - orderDetail.getQuantity());
        productService.updateProduct(product);
    }

    public Double getTotalSalesForToday(String token, Long storeId) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            return orderRepository.getTotalSalesForToday(storeId);
        } else {
            return 0.0;
        }
    }

    public Double getTotalSalesForAllStoresForToday(String token) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            return orderRepository.getTotalSalesForAllStoresForToday();
        } else {
            return 0.0;
        }
    }

    public List<ProductSalesResponse> getStoreMonthlySalesInfo(String token, int month, int year, Long storeId) {
        User user = userService.getUserById(jwtManager.getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            List<Object[]> objects = orderRepository.getStoreMonthlySalesInfo(month, year, storeId);
            List<ProductSalesResponse> salesList = objects.stream()
                    .map(obj -> new ProductSalesResponse(
                            ((Number) obj[0]).longValue(),
                            ((Number) obj[1]).doubleValue()))
                    .collect(Collectors.toList());
            return salesList;
        } else {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }
}