package com.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.UUID;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private String role; // e.g., "CUSTOMER" or "ADMIN"
    private String address;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
}
