package com.mycompany.e_commerce.service;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.entity.OrderDetail;
import com.mycompany.e_commerce.repository.OrderDetailRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    protected void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    protected OrderDetail findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}