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
public class AddNewStoreAdminRequest {
    private String name;
    private Long storeOwnerId;
}
