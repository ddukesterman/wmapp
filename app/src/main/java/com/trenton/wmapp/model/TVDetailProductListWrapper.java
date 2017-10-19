package com.trenton.wmapp.model;

import java.io.Serializable;

public class TVDetailProductListWrapper implements Serializable {

    private Products products;
    private int position;

    public TVDetailProductListWrapper(Products products, int position) {
        this.products = products;
        this.position = position;
    }

    public Products getProducts() {
        return products;
    }

    public int getPosition() {
        return position;
    }
}
