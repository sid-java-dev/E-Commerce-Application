package com.e_commerce.service.otp_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpServiceFactory {

    private final EmailOtpService emailOtpService;
    private final SmsOtpService smsOtpService;
    public OtpService createOtp(String method) {
        if (method.equals("email")) {
            return emailOtpService;
        } else if (method.equals("phone")) {
            return smsOtpService;
        }
        throw new IllegalArgumentException("Invalid Otp Method");
    }
}
