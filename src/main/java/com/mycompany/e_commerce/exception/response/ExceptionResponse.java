package com.mycompany.e_commerce.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private int statusCode;
    private String message;
    private String timeStamp;
}
