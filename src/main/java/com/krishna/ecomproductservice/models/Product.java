package com.krishna.ecomproductservice.models;

import lombok.Data;

@Data
public class Product extends BaseModel {
    private String productName;
    private String description;
    private Double price;
    private String image;
    private Category category;
}
