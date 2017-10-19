package com.trenton.wmapp.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import com.trenton.wmapp.R;
import com.trenton.wmapp.adapter.TVDetailAdapter;
import com.trenton.wmapp.model.TVDetailProductListWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class TVDetailActivity extends AppCompatActivity {

    private static final String EXTRA_WRAPPER = "EXTRA_WRAPPER";

    private TVDetailProductListWrapper wrapper;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private TVDetailAdapter tvDetailAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static Intent newIntent(Context context, TVDetailProductListWrapper wrapper) {
        Timber.d("[newIntent]");
        Intent intent = new Intent(context, TVDetailActivity.class);
        intent.putExtra(EXTRA_WRAPPER, wrapper);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Timber.d("[onCreate]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetail);
        ButterKnife.bind(this);

        wrapper = (TVDetailProductListWrapper) getIntent().getSerializableExtra(EXTRA_WRAPPER);

        setupRecyclerView();

        tvDetailAdapter.swapData(wrapper.getProducts());

        recyclerView.scrollToPosition(wrapper.getPosition());
    }

    private void setupRecyclerView() {
        Timber.d("[setupRecyclerView]");
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        tvDetailAdapter = new TVDetailAdapter();
        recyclerView.setAdapter(tvDetailAdapter);

    }

}
