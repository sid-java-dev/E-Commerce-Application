package com.e_commerce.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpGenerator {

    //method to generate Otp for a specific Length
    public String generateNumericOtp(int length){
        Random random=new Random();
        StringBuilder otp=new StringBuilder();
        for(int i=0;i<length;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();

    }
}
