package com.bytebuilder.EstateManager.controllers;

import com.bytebuilder.EstateManager.dtos.requests.CreateNewResidentRequest;
import com.bytebuilder.EstateManager.dtos.requests.LoginRequest;
import com.bytebuilder.EstateManager.dtos.responses.LoginResponse;
import com.bytebuilder.EstateManager.dtos.responses.ResidentResponse;
import com.bytebuilder.EstateManager.service.ResidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resident")
public class ResidentController {

    private final ResidentService residentService;
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResidentResponse> registerResident(@RequestBody CreateNewResidentRequest request) {
        ResidentResponse residentResponse = residentService.createNewResident(request);
        return ResponseEntity.ok(residentResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginResident(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = residentService.loginResident(request);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Server is running!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponse> getResidentById(@PathVariable("id") String residentId) {
        ResidentResponse residentDto = residentService.getResidentById(residentId);
        return ResponseEntity.ok(residentDto);
    }

    @PostMapping("/{id}/generate-otp")
    public ResponseEntity<String> generateOtp(@PathVariable("id") String residentId) {
        String otp = residentService.generateOtpForResident(residentId);
        return ResponseEntity.ok("OTP generated: " + otp);
    }
}