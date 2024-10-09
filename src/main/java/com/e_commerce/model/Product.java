package com.e_commerce.model;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private Category category;
    private String description;
    private Brand brand;
    private int stockQuality;
    private double discountPrice;

    public Product() {
        this.id = UUID.randomUUID().toString();
    }
}
