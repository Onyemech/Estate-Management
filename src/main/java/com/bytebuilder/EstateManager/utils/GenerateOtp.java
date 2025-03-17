package com.bytebuilder.EstateManager.utils;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateOtp {

    Random rand = new Random();
    public String getGeneratedOtp(){
        StringBuilder otp = new StringBuilder();
        for(int count = 0; count < 4; count++){
            int randomNumber = rand.nextInt(10);
            otp.append(randomNumber);
        }
        return otp.toString();
    }
}
