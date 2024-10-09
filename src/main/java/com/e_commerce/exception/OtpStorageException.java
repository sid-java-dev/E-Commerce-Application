package com.e_commerce.exception;


import org.springframework.data.redis.RedisConnectionFailureException;

public class OtpStorageException extends RuntimeException {
    public OtpStorageException(String message, RuntimeException cause) {
        super(message,cause);
    }
}
