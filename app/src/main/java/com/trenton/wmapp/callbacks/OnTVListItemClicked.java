package com.trenton.wmapp.callbacks;

import com.trenton.wmapp.model.Products;

public interface OnTVListItemClicked {
    void onItemClicked(int position, Products products);
}