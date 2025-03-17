package com.bytebuilder.EstateManager.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewSecurity {
    private String firstName;
    private String lastName;
    private String password;
}
