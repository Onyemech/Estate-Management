package com.bytebuilder.EstateManager.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "Resident")
public class Resident {

    @Id
    private String id;
    private String name;
    private String password;
    private String email;
    private String otp;
    private String phone;
    private long otpExpiryTime;
}
