package com.bytebuilder.EstateManager.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String residentId;
    private String name;

}
