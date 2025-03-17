package com.bytebuilder.EstateManager.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewResidentRequest {

    private String name;
    private String password;
    private String email;
    private String phone;
}
