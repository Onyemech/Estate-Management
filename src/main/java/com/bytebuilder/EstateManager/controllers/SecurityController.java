package com.bytebuilder.EstateManager.controllers;


import com.bytebuilder.EstateManager.dtos.requests.ValidateOtpRequestAndIssuePassDto;
import com.bytebuilder.EstateManager.dtos.requests.VisitorRequest;
import com.bytebuilder.EstateManager.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtpAndIssuePass(@RequestBody ValidateOtpRequestAndIssuePassDto request) {
        boolean isValid = securityService.validateOtpAndIssuePass(request);

        if (isValid) {
            return ResponseEntity.ok("OTP validated. Visitor pass issued.");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP or expired.");
        }
    }
}