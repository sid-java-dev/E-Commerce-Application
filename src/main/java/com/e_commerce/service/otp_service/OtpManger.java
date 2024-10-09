package com.e_commerce.service.otp_service;

import com.e_commerce.service.UserService;
import com.e_commerce.util.OtpGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpManger {
    private final OtpGenerator otpGenerator;
    private final OtpStorageService storageService;
    private final OtpServiceFactory otpServiceFactory;
    private final UserService userService;

    // common method to handle otp generation and sending
    public void handleOtpProcess(String emailOrPhone){
        // generate the otp
        String otp = otpGenerator.generateNumericOtp(6);

        // stored otp in Redis
        storageService.storeOtp(emailOrPhone,otp);

        //determine whether it is phone or email
        String method=emailOrPhone.contains("@") ?"email":"phone";
        OtpService otpService = otpServiceFactory.createOtp(method);

        // Step 5: Use the appropriate service to send the OTP
        otpService.sendOtp(emailOrPhone, otp);
    }


    public boolean verifyOtpByEmailOrPhone(String emailOrPhone, String otp) {

        // Get the stored Otp
         String storedOtp=storageService.getOtp(emailOrPhone);

         if(storedOtp ==null || !storedOtp.equals(otp)){
             return false;
         }
         //if block will false that means Otp are match
        //verify user status to database
         userService.UpdateUserStatusToVerify(emailOrPhone);

         return true;

    }
}
