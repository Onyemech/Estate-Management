package com.bytebuilder.EstateManager.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;
    private String phone;
    private String token;
}
