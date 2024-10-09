package com.e_commerce.service.impl;

import com.e_commerce.exception.UserException;
import com.e_commerce.model.User;
import com.e_commerce.payload.UserRequest;
import com.e_commerce.repository.UserRepository;
import com.e_commerce.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRequest request) {
        //verify that user is already Exists or not
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserException("User is already exists");
        }
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_CUSTOMER")
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmailOrPhone(String emailOrPhone) {
   Optional<User>user;

   //check if input is email
        if(emailOrPhone.contains("@")){
            user=userRepository.findByEmail(emailOrPhone);
        }else{
            //otherwise, It is a phone
           user= userRepository.findByPhone(emailOrPhone);
        }

      //If user is not found
      if(user.isPresent()){
          return user.get();
      }else{
          throw new UserException("User not found with email or phone: " + emailOrPhone);
      }

    }

    @Override
    public void UpdateUserStatusToVerify(String emailOrPhone) {
        Optional<User>user;
        //check if input is email
        if(emailOrPhone.contains("@")){
            user=userRepository.findByEmail(emailOrPhone);
        }else{
            //otherwise, It is a phone
            user= userRepository.findByPhone(emailOrPhone);
        }
        if(user.isPresent()){
            User u=user.get();

            //set verification based on whether it is an email or phone

            if(emailOrPhone.contains("@")){
                u.setEmailVerified(true);
            }else{
                // set phone as verified
                u.setPhoneVerified(true);
            }
            userRepository.save(u);
        }else{
            throw new UserException("User not found for " + emailOrPhone);
        }

    }
}
