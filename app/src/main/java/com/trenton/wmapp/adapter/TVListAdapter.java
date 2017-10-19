package com.trenton.wmapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.trenton.wmapp.R;
import com.trenton.wmapp.adapter.viewholder.TVListViewHolder;
import com.trenton.wmapp.callbacks.OnTVListItemClicked;
import com.trenton.wmapp.model.Product;
import com.trenton.wmapp.model.Products;

import timber.log.Timber;

public class TVListAdapter extends RecyclerView.Adapter<TVListViewHolder> {

    private Products products = new Products();
    private OnTVListItemClicked listener;

    public TVListAdapter(OnTVListItemClicked listener) {
        this.listener = listener;
    }

    public void appendItems(Products appendProducts) {
        if(appendProducts.size() > 0) {

            products.addAll(appendProducts);

            if(products.size() > 0) {
                notifyItemRangeChanged(products.size() - appendProducts.size(), appendProducts.size());
            } else {
                notifyItemRangeChanged(0, products.size());
            }
        }
    }

    @Override
    public TVListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.tv_list_item, parent, false);
        return new TVListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TVListViewHolder holder, int position) {
        Product item = products.get(holder.getAdapterPosition());
        if(item != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(holder.getAdapterPosition(), products);
                }
            });
            if(item.getProductName() != null) {
                holder.name.setText(item.getProductName());
            } else {
                holder.name.setText("");
            }
            if (item.getShortDescription() != null) {
                holder.desc.setText(Html.fromHtml(item.getShortDescription()));
            } else {
                holder.desc.setText("");
            }

            if(item.getPrice() != null) {
                holder.price.setText(item.getPrice());
            } else {
                holder.price.setText("");
            }
            String rating = String.format("%.1f", item.getReviewRating());

            holder.rating.setText(String.format(holder.itemView.getContext().getString(R.string.review_rating), rating, item.getReviewCount()));

            if(item.getProductImage() != null) {
                Glide.with(holder.itemView.getContext().getApplicationContext())
                        .load(item.getProductImage())
                        .crossFade()
                        .into(holder.image);
            }
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
