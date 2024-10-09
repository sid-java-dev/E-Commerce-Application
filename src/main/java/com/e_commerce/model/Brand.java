package com.e_commerce.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "brand")
@Data
public class Brand {
    @Id
    private String id;
    private String name;

    public Brand(){
        this.id= UUID.randomUUID().toString();
    }
}
