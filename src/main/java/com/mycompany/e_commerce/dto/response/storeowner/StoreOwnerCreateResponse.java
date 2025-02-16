package com.mycompany.e_commerce.dto.response.storeowner;

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
public class StoreOwnerCreateResponse {
    private String name;
    private String email;
}
