package com.mycompany.e_commerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.e_commerce.dto.request.admin.AdminRegisterRequest;
import com.mycompany.e_commerce.dto.request.admin.AdminUpdateRequest;
import com.mycompany.e_commerce.dto.request.customer.CustomerRegisterRequest;
import com.mycompany.e_commerce.dto.request.customer.CustomerUpdateRequest;
import com.mycompany.e_commerce.dto.request.order.CreateOrderRequest;
import com.mycompany.e_commerce.dto.request.storeowner.StoreOwnerRegisterRequest;
import com.mycompany.e_commerce.dto.request.user.LoginRequest;
import com.mycompany.e_commerce.dto.response.admin.AdminCreateResponse;
import com.mycompany.e_commerce.dto.response.customer.CustomerCreateResponse;
import com.mycompany.e_commerce.dto.response.storeowner.StoreOwnerCreateResponse;
import com.mycompany.e_commerce.entity.User;
import com.mycompany.e_commerce.entity.enums.Role;
import com.mycompany.e_commerce.exception.customexception.CustomeException;
import com.mycompany.e_commerce.exception.customexception.TokenInvalidException;
import com.mycompany.e_commerce.exception.customexception.UnauthorizedAccessException;
import com.mycompany.e_commerce.exception.customexception.UserAlreadyExistException;
import com.mycompany.e_commerce.exception.customexception.UserNameOrPasswordWrongException;
import com.mycompany.e_commerce.exception.customexception.UserNotFoundException;
import com.mycompany.e_commerce.repository.UserRepository;
import com.mycompany.e_commerce.security.JWTManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final JWTManager jwtManager;

    @Transactional
    public CustomerCreateResponse createCustomer(CustomerRegisterRequest customer) {
        userRepository.findByUserName(customer.getUserName()).ifPresent(user -> {
            throw new UserAlreadyExistException(CustomeException.USER_ALREADY_EXISTS);
        });
        User user = User.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .userName(customer.getUserName())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .role(Role.CUSTOMER.toString())
                .createAt(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return CustomerCreateResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public AdminCreateResponse createAdmin(AdminRegisterRequest admin) {
        userRepository.findByUserName(admin.getUserName()).ifPresent(user -> {
            throw new UserAlreadyExistException(CustomeException.USER_ALREADY_EXISTS);
        });
        User user = User.builder()
                .name(admin.getName())
                .email(admin.getEmail())
                .userName(admin.getUserName())
                .password(admin.getPassword())
                .phone(admin.getPhone())
                .role(Role.ADMIN.toString())
                .createAt(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return AdminCreateResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public StoreOwnerCreateResponse createStoreOwner(StoreOwnerRegisterRequest storeOwner) {
        userRepository.findByUserName(storeOwner.getUserName()).ifPresent(user -> {
            throw new UserAlreadyExistException(CustomeException.USER_ALREADY_EXISTS);
        });
        User user = User.builder()
                .name(storeOwner.getName())
                .email(storeOwner.getEmail())
                .userName(storeOwner.getUserName())
                .password(storeOwner.getPassword())
                .phone(storeOwner.getPhone())
                .role(Role.SELLER.toString())
                .createAt(LocalDateTime.now().toString())
                .build();
        userRepository.save(user);
        return StoreOwnerCreateResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public String login(LoginRequest login) {
        User user = userRepository.findByUserNameAndPassword(login.getUserName(), login.getPassword()).orElseThrow(
                () -> new UserNameOrPasswordWrongException(CustomeException.USER_NAME_OR_PASSWORD_IS_WRONG));
        return jwtManager.generateToken(user.getId());
    }

    @Transactional
    public void createOrder(String token, CreateOrderRequest createOrderRequest) {
        User user = getUserById(getUserIdFromToken(token));
        orderService.createOrder(user, createOrderRequest);
    }

    protected User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(CustomeException.USER_NOT_FOUND));
    }

    protected List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateCustomer(String token, CustomerUpdateRequest customer) {
        Long id = getUserIdFromToken(token);
        User currentUser = getUserById(customer.getId());
        if (id == currentUser.getId()) {
            currentUser.setEmail(customer.getEmail());
            currentUser.setUserName(customer.getUserName());
            currentUser.setPassword(customer.getPassword());
            currentUser.setPhone(customer.getPhone());
            currentUser.setAddress(customer.getAddress());
            currentUser.setUpdateAt(
                    LocalDateTime.now().toString());
            userRepository.save(currentUser);
        } else {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }

    @Transactional
    public void updateAdmin(String token, AdminUpdateRequest admin) {
        Long id = getUserIdFromToken(token);
        User currentUser = getUserById(admin.getId());
        if (id == currentUser.getId()) {
            currentUser.setEmail(admin.getEmail());
            currentUser.setUserName(admin.getUserName());
            currentUser.setPassword(admin.getPassword());
            currentUser.setPhone(admin.getPhone());
            currentUser.setUpdateAt(
                    LocalDateTime.now().toString());
            userRepository.save(currentUser);
        } else {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }

    @Transactional
    public void deleteAdmin(String token, Long id) {
        User user = getUserById(getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString()) && !user.getId().equals(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UnauthorizedAccessException(CustomeException.UNAUTHORIZED_ACCESS);
        }
    }

    public Double getTotalSalesForToday(String token, Long storeId) {
        User user = getUserById(getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            return orderService.getTotalSalesForToday(storeId);
        } else {
            return 0.0;
        }
    }

    public Double getTotalSalesForAllStoresForToday(String token) {
        User user = getUserById(getUserIdFromToken(token));
        if (user.getRole().equals(Role.ADMIN.toString())) {
            return orderService.getTotalSalesForAllStoresForToday(token);
        } else {
            return 0.0;
        }
    }

    private Long getUserIdFromToken(String token) {
        return jwtManager.validToken(token)
                .orElseThrow(() -> new TokenInvalidException(CustomeException.TOKEN_INVALID));
    }
}