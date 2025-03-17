package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.dtos.requests.CreateNewResidentRequest;
import com.bytebuilder.EstateManager.dtos.requests.LoginRequest;
import com.bytebuilder.EstateManager.dtos.responses.LoginResponse;
import com.bytebuilder.EstateManager.dtos.responses.ResidentResponse;

public interface ResidentService {
    ResidentResponse createNewResident(CreateNewResidentRequest request);
    LoginResponse loginResident(LoginRequest request);
    ResidentResponse getResidentById(String id);
    String generateOtpForResident(String residentId);
}