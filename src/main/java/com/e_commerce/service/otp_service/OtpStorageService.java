package com.e_commerce.service.otp_service;

public interface OtpStorageService {
     void storeOtp(String phoneOrEmail ,String otp);
    String getOtp(String phoneOrEmail);
  //  boolean isOtpVerify(String phoneOrEmail, String otp);
}
