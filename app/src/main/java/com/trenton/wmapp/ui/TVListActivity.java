package com.trenton.wmapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trenton.wmapp.R;
import com.trenton.wmapp.adapter.TVListAdapter;
import com.trenton.wmapp.callbacks.OnTVListItemClicked;
import com.trenton.wmapp.model.Products;
import com.trenton.wmapp.model.TVDetailProductListWrapper;
import com.trenton.wmapp.service.WmController;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.SingleSubscriber;
import timber.log.Timber;

public class TVListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.error_message)
    View errorMessage;
    @BindView(R.id.progress)
    View progress;

    private WmController wmController = new WmController();
    private TVListAdapter tvListAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvlist);
        ButterKnife.bind(this);
        setupRecyclerView();

        fetchItems();
    }

    private void setupRecyclerView() {
        tvListAdapter = new TVListAdapter(new OnTVListItemClicked() {
            @Override
            public void onItemClicked(int position, Products products) {
                startActivity(TVDetailActivity.newIntent(TVListActivity.this, new TVDetailProductListWrapper(products, position)));
            }
        });

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(tvListAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!wmController.isLoading() && !wmController.hasFetchedAll()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= wmController.getPageSize()) {
                        fetchItems();
                    }
                }
            }
        });
    }

    private void fetchItems() {
        Timber.d("[fetchItems]");
        wmController.singleResponseFromWmService(new SingleSubscriber<Products>() {
            @Override
            public void onSuccess(Products products) {
                Timber.d("onSuccess");
                tvListAdapter.appendItems(products);
                recyclerView.setVisibility(View.VISIBLE);
                errorMessage.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable error) {
                Timber.d("onError " + error.getMessage());
                recyclerView.setVisibility(View.GONE);
                errorMessage.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }
        });
    }
}
