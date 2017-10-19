package com.trenton.wmapp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trenton.wmapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TVDetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image)
    public ImageView image;
    @BindView(R.id.name)
    public TextView name;
    @BindView(R.id.desc)
    public TextView desc;
    @BindView(R.id.price)
    public TextView price;
    @BindView(R.id.rating)
    public TextView rating;
    @BindView(R.id.in_stock)
    public TextView inStock;
    @BindView(R.id.add_to_cart)
    public TextView addToCart;

    public TVDetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
