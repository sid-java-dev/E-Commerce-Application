package com.e_commerce.service;

import com.e_commerce.model.User;
import com.e_commerce.payload.UserRequest;

public interface UserService {
    User createUser(UserRequest request);
    User getUserByEmailOrPhone(String emailOrPhone);

    void UpdateUserStatusToVerify(String emailOrPhone);
}
