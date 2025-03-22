package com.bytebuilder.EstateManager.service;

import com.bytebuilder.EstateManager.data.models.Resident;
import com.bytebuilder.EstateManager.data.repositories.ResidentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ResidentRepository residentRepository;

    public CustomUserDetailsService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Resident resident = residentRepository.findByEmail(email)

                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                resident.getEmail(),
                resident.getPassword(),
                resident.getAuthorities());
    }
}