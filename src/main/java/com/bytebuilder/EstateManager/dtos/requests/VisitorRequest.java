package com.bytebuilder.EstateManager.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorRequest {
    private String residentPhone;
    private String otpValue;
    private String visitorName;
    private String phoneNumber;
}