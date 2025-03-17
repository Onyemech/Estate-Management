package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.data.models.Resident;
import com.bytebuilder.EstateManager.data.repositories.ResidentRepository;
import com.bytebuilder.EstateManager.dtos.requests.CreateNewResidentRequest;
import com.bytebuilder.EstateManager.dtos.requests.LoginRequest;
import com.bytebuilder.EstateManager.dtos.responses.LoginResponse;
import com.bytebuilder.EstateManager.dtos.responses.ResidentResponse;
import com.bytebuilder.EstateManager.exceptions.ResourceNotFoundException;
import com.bytebuilder.EstateManager.utils.GenerateOtp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private GenerateOtp generateOtp;

    @Override
    public ResidentResponse createNewResident(CreateNewResidentRequest request) {
        boolean residentExists = residentRepository.existsByEmail(request.getEmail());
        if (residentExists) {
            throw new ResourceNotFoundException("Resident already exists");
        }
        Resident resident = new Resident();
        resident.setName(request.getName());
        resident.setPassword(request.getPassword());
        resident.setEmail(request.getEmail());
        resident.setPhone(request.getPhone());
        residentRepository.save(resident);

        ResidentResponse response = new ResidentResponse();
        response.setId(resident.getId());
        response.setEmail(request.getEmail());
        response.setName(request.getName());

        return response;
    }

    @Override
    public LoginResponse loginResident(LoginRequest request) {
        Resident resident = residentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));
        if (!request.getPassword().equals(resident.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResidentId(resident.getId());
        loginResponse.setName(resident.getName());

        return loginResponse;
    }

    @Override
    public ResidentResponse getResidentById(String id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));

        ResidentResponse response = new ResidentResponse();
        response.setId(resident.getId());
        response.setName(resident.getName());
        response.setEmail(resident.getEmail());

        return response;
    }

    @Override
    public String generateOtpForResident(String residentId) {
        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not found"));

        String otp = generateOtp.getGeneratedOtp();
        long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);

        resident.setOtp(otp);
        resident.setOtpExpiryTime(expiryTime);
        residentRepository.save(resident);

        return otp;
    }
}