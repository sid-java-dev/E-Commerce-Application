package com.e_commerce.service.otp_service;

import com.e_commerce.util.TwilioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsOtpService implements OtpService{
    private final TwilioService twilioService;

    @Override
    public void sendOtp(String contact, String otp) {
      twilioService.sendOtpSms(contact,otp);
    }
}
