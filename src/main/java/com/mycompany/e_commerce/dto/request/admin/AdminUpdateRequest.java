package com.mycompany.e_commerce.dto.request.admin;

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
public class AdminUpdateRequest {
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String phone; 
}
