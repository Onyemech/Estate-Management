package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.dtos.requests.ValidateOtpRequestAndIssuePassDto;

public interface SecurityService {
    boolean validateOtpAndIssuePass(ValidateOtpRequestAndIssuePassDto request);
}