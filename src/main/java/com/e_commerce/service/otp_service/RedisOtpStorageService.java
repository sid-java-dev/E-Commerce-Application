package com.e_commerce.service.otp_service;

import com.e_commerce.exception.OtpStorageException;
import com.e_commerce.exception.OtpVerificationException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisOtpStorageService implements OtpStorageService{

    private final RedisTemplate<String,String> redisTemplate;

    public RedisOtpStorageService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //stored otp in redis with expiration time

    public void storeOtp(String phoneOrEmail ,String otp){
        try {
            redisTemplate.opsForValue().set(phoneOrEmail, otp, 2, TimeUnit.MINUTES);
            System.out.println("OTP stored successfully for: " + phoneOrEmail);
        } catch (RedisConnectionFailureException e) {
            // Handle Redis connection failure
            throw new OtpStorageException("Failed to store OTP due to Redis connection issues.",e);
        } catch (DataAccessException e) {
            // Handle other Redis data access exceptions
            throw new OtpStorageException("Failed to store OTP due to Redis data access issue.",e);
        }
    }

    //Retrieve Otp from Redis
    public String getOtp(String phoneOrEmail) {
        try {
            return redisTemplate.opsForValue().get(phoneOrEmail);
        } catch (RedisConnectionFailureException e) {
            // Handle Redis connection failure
            throw new OtpStorageException("Failed to retrieve OTP due to Redis connection issues.", e);
        } catch (DataAccessException e) {
            // Handle other Redis data access exceptions
            throw new OtpStorageException("Failed to retrieve OTP due to Redis data access issue.", e);
        }
    }

    }

