package com.mycompany.e_commerce.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String userName;
    private String password;
    private String role;
    private String phone;   
    private String address;
    private String createAt;
    private String updateAt;
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Store store;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Order> orders;
}
