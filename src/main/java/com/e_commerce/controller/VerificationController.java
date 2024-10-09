package com.e_commerce.controller;


import com.e_commerce.model.User;
import com.e_commerce.service.UserService;
import com.e_commerce.service.otp_service.OtpManger;
import com.e_commerce.service.otp_service.OtpService;
import com.e_commerce.service.otp_service.OtpStorageService;
import com.e_commerce.util.OtpGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verification")
public class VerificationController {

    private final OtpManger otpManger;
    private final UserService userService;


    public VerificationController(UserService userService, OtpGenerator otpGenerator, OtpStorageService storageService, OtpManger otpManger) {
        this.userService = userService;
        this.otpManger = otpManger;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String emailOrPhone){
        // get the user by email or phone
        User user=userService.getUserByEmailOrPhone(emailOrPhone);

      // handle  otp process by Manger
        otpManger.handleOtpProcess(emailOrPhone);

        return ResponseEntity.ok("OTP sent successfully to " + emailOrPhone);

    }
}
