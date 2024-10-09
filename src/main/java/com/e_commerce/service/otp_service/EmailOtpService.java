package com.e_commerce.service.otp_service;

import com.e_commerce.util.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailOtpService implements OtpService {

private final EmailService emailService;
    @Override
    public void sendOtp(String contact, String otp) {
     emailService.sendOtpEmail(contact,otp);

    }
}
