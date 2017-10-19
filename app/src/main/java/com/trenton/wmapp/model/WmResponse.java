package com.trenton.wmapp.model;

import java.io.Serializable;

public class WmResponse implements Serializable {

    private Products products;
    private Integer totalProducts;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer status;
    private String kind;
    private String etag;

    public Products getProducts() {
        return products;
    }

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getStatus() {
        return status;
    }

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }
}
