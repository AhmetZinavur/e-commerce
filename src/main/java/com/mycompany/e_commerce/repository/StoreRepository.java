package com.mycompany.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.e_commerce.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByUserId(Long storeOwnerId);
}
