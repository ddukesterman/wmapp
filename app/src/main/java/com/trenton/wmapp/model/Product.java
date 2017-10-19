package com.trenton.wmapp.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String productId;
    private String productName;
    private  String shortDescription;
    private String longDescription;
    private String price;
    private String productImage;
    private Double reviewRating;
    private Long reviewCount;
    private Boolean inStock;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public Double getReviewRating() {
        return reviewRating;
    }

    public Long getReviewCount() {
        return reviewCount;
    }

    public Boolean getInStock() {
        return inStock;
    }
}
