package com.trenton.wmapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.trenton.wmapp.R;
import com.trenton.wmapp.adapter.viewholder.TVDetailViewHolder;
import com.trenton.wmapp.model.Product;
import com.trenton.wmapp.model.Products;

import timber.log.Timber;

public class TVDetailAdapter extends RecyclerView.Adapter<TVDetailViewHolder> {

    private Products products = new Products();

    public void swapData(Products products) {
        this.products = products;
        notifyItemRangeChanged(0, products.size());
    }

    @Override
    public TVDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.tv_detail_item, parent, false);
        return new TVDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TVDetailViewHolder holder, int position) {
        Timber.d("onBindViewHolder " + position);
        Product item = products.get(holder.getAdapterPosition());
        if(item != null) {
            if(item.getProductName() != null) {
                holder.name.setText(item.getProductName());
            } else {
                holder.name.setText("");
            }
            if (item.getLongDescription() != null) {
                holder.desc.setText(Html.fromHtml(item.getLongDescription()));
                holder.desc.setVisibility(View.VISIBLE);
            } else {
                holder.desc.setVisibility(View.INVISIBLE);
            }

            if(item.getPrice() != null) {
                holder.price.setText(item.getPrice());
            } else {
                holder.price.setText("");
            }
            String rating = String.format("%.1f", item.getReviewRating());

            holder.rating.setText(String.format(holder.itemView.getContext().getString(R.string.review_rating), rating, item.getReviewCount()));

            if(item.getInStock()) {
                holder.inStock.setText(holder.itemView.getResources().getString(R.string.in_stock));
                holder.inStock.setTextColor(holder.itemView.getResources().getColor(R.color.tv_detail_item_stock_green));
                holder.addToCart.setEnabled(true);
            } else {
                holder.inStock.setText(holder.itemView.getResources().getString(R.string.outof_stock));
                holder.inStock.setTextColor(holder.itemView.getResources().getColor(R.color.tv_detail_item_stock_gray));
                holder.addToCart.setEnabled(false);
            }

            Glide.with(holder.itemView.getContext().getApplicationContext())
                    .load(item.getProductImage())
                    .crossFade()
                    .into(holder.image);
        } else{
            Timber.e("Item at position " + position + " is null");
        }
    }

    @Override
    public int getItemCount() {
        if(products != null && products.size() > 0) { return products.size(); }
        return 0;
    }
}
