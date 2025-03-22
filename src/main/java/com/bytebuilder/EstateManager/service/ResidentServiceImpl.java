package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.data.models.Resident;
import com.bytebuilder.EstateManager.data.repositories.ResidentRepository;
import com.bytebuilder.EstateManager.dtos.requests.CreateNewResidentRequest;
import com.bytebuilder.EstateManager.dtos.requests.LoginRequest;
import com.bytebuilder.EstateManager.dtos.responses.LoginResponse;
import com.bytebuilder.EstateManager.dtos.responses.ResidentResponse;
import com.bytebuilder.EstateManager.exceptions.ResourceNotFoundException;
import com.bytebuilder.EstateManager.utils.GenerateOtp;
import com.bytebuilder.EstateManager.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private GenerateOtp generateOtp;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResidentResponse createNewResident(CreateNewResidentRequest request) {
        boolean residentExistsByEmail = residentRepository.existsByEmail(request.getEmail());
        boolean residentExistsByPhone = residentRepository.existsByPhone(request.getPhone());
        if (residentExistsByEmail || residentExistsByPhone) {
            throw new ResourceNotFoundException("Resident already exists");
        }

        String userPassword = request.getPassword();
        String hashedPassword = PasswordUtil.hashPassword(userPassword);

        Resident resident = new Resident();
        resident.setName(request.getName());
        resident.setPassword(hashedPassword);
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
        String userPassword = request.getPassword();
        String hashedPassword = PasswordUtil.hashPassword(userPassword);

        Resident resident = residentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));
        if (!PasswordUtil.verifyPassword(request.getPassword(), resident.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(resident.getEmail());

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setResidentId(resident.getId());
        loginResponse.setName(resident.getName());
        loginResponse.setToken(token);

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