package com.mycompany.e_commerce.dto.request.storeowner;

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
public class StoreOwnerRegisterRequest {
    private String name;
    private String email;
    private String userName;
    private String password;
    private String phone;
}
