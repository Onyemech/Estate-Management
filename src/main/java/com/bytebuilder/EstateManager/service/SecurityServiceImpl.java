package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.data.models.Resident;
import com.bytebuilder.EstateManager.data.models.VisitorsPass;
import com.bytebuilder.EstateManager.data.repositories.ResidentRepository;
import com.bytebuilder.EstateManager.data.repositories.VisitorsPassRepository;
import com.bytebuilder.EstateManager.dtos.requests.ValidateOtpRequestAndIssuePassDto;
import com.bytebuilder.EstateManager.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private VisitorsPassRepository visitorsPassRepository;

    @Override
    public boolean validateOtpAndIssuePass(ValidateOtpRequestAndIssuePassDto request) {
        Resident resident = residentRepository.findByPhone(request.getResidentPhone())
                .orElseThrow(() -> new ResourceNotFoundException("Resident with "+request.getResidentPhone()+" not found"));

        boolean isOtpValid = resident.getOtp() != null
                && resident.getOtp().equals(request.getOtpValue())
                && System.currentTimeMillis() <= resident.getOtpExpiryTime();

        if (!isOtpValid) {
            return false;
        }
        VisitorsPass pass = new VisitorsPass();
        pass.setVisitorName(request.getVisitorName());
        pass.setResidentPhone(request.getResidentPhone());
        pass.setPhoneNumber(request.getPhoneNumber());
        pass.setEntryTime(System.currentTimeMillis());
        pass.setExpiryTime(System.currentTimeMillis() + (2 * 60 * 60 * 1000));
        pass.setStatus("ACTIVE");

        visitorsPassRepository.save(pass);
        resident.setOtp(null);
        residentRepository.save(resident);

        return true;
    }

    public void handleVisitorExit(String passId) {
        VisitorsPass pass = visitorsPassRepository.findById(passId)
                .orElseThrow(() -> new ResourceNotFoundException("Pass not found"));

        pass.setExpiryTime(System.currentTimeMillis());
        pass.setStatus("EXPIRED");
        visitorsPassRepository.save(pass);
    }

}