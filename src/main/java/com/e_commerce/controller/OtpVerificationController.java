package com.e_commerce.controller;

import com.e_commerce.service.otp_service.OtpManger;
import com.e_commerce.service.otp_service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verification")
public class OtpVerificationController {

    private  final OtpManger otpManger;

    public OtpVerificationController(OtpManger otpManger) {
        this.otpManger = otpManger;
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String>verifyOtp(@RequestParam String emailOrPhone,
                                           @RequestParam String otp){
        boolean isVerified = otpManger.verifyOtpByEmailOrPhone(emailOrPhone, otp);

        if(isVerified){
            return ResponseEntity.ok("Otp Verified Successfully!!");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }

    }
}
