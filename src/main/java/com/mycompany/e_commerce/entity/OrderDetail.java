package com.mycompany.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;
    private int quantity;
    private Double price;
    private String createAt;
}