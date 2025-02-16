package com.mycompany.e_commerce.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Entity(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private Set<Product> products;
    @OneToOne
    @JoinColumn(name = "store_owner_id")
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Order> orders;
    private String createAt;
    private String updateAt;
}