package com.mycompany.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycompany.e_commerce.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value="SELECT COALESCE(SUM(orders.total), 0) FROM orders WHERE orders.create_at::DATE = CURRENT_DATE AND orders.store_id = store_id", nativeQuery = true)    
    Double getTotalSalesForToday(Long storeId);
    @Query(value="SELECT COALESCE(SUM(orders.total), 0) FROM orders WHERE orders.create_at::DATE = CURRENT_DATE", nativeQuery = true)
    Double getTotalSalesForAllStoresForToday();
}