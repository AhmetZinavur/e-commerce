package com.mycompany.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.e_commerce.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT COALESCE(SUM(orders.total), 0) FROM orders WHERE orders.create_at::DATE = CURRENT_DATE AND orders.store_id = store_id", nativeQuery = true)
    Double getTotalSalesForToday(Long storeId);

    @Query(value = "SELECT COALESCE(SUM(orders.total), 0) FROM orders WHERE orders.create_at::DATE = CURRENT_DATE", nativeQuery = true)
    Double getTotalSalesForAllStoresForToday();

    @Query(value = "select order_details.product_id, sum(order_details.quantity * order_details.unit_price) from orders inner join order_details on orders.id = order_details.order_id where orders.status = 'APPROVED' and orders.store_id = :storeId and extract(month from orders.update_at::date) = :m and extract(year from orders.update_at::date) = :y group by order_details.product_id;", nativeQuery = true)
    List<Object[]> getStoreMonthlySalesInfo(@Param("m") int m, @Param("y") int y, @Param("storeId") Long storeId);
}