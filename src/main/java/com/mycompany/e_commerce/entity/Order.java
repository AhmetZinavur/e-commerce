package com.mycompany.e_commerce.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createAt;
    private String updateAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;
    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;
    private Double total;
    private String status;
}