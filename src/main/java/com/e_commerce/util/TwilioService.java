package com.e_commerce.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TwilioService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioNumber;

    public void sendOtpSms(String toPhoneNumber, String otp) {
// Ensure the phone number is in E.164 format
        toPhoneNumber=toPhoneNumber.startsWith("+91")?toPhoneNumber:"+91"+toPhoneNumber;
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(twilioNumber),
                        "Your OTP is: " + otp)
                .create();

        System.out.println("SMS sent with SID: " + message.getSid());
    }
}
