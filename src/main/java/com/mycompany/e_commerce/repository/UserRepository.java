package com.mycompany.e_commerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.e_commerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    User findByEmail(String email);
    Optional <User> findByUserNameAndPassword(String userName, String password);
    boolean existsByUserNameOrEmail(String userName, String email);
}
