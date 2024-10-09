package com.e_commerce.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "category")
@Data
public class Category {
    @Id
    private String id;
    private String name;

    public Category(){
        this.id= UUID.randomUUID().toString();
    }
}
