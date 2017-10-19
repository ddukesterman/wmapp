package com.trenton.wmapp.service;

import com.trenton.wmapp.manager.RetrofitManager;
import com.trenton.wmapp.model.Products;
import com.trenton.wmapp.model.WmResponse;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WmController {

    private static final String API_KEY = "3e2f38d0-d26c-416e-b028-1d5e0f8350ed";
    private WmService service;
    private int totalProducts = 0;
    private int currentPageNumber = 1;
    private int pageSize = 30;
    private boolean isLoading = false;
    private boolean firstFetch = true;

    public void singleResponseFromWmService(final SingleSubscriber<Products> products) {
        if(!firstFetch) {
            currentPageNumber++;
        }
        isLoading = true;

        service = RetrofitManager.getInstance().getDefaultRetrofitInstance().create(WmService.class);
        Single<WmResponse> tvList = service.getTVList(API_KEY, currentPageNumber, pageSize);
        tvList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<WmResponse>() {
                    @Override
                    public void onSuccess(WmResponse value) {
                        products.onSuccess(value.getProducts());
                        if (firstFetch) {
                            firstFetch = false;
                            totalProducts = value.getTotalProducts();
                        }

                        isLoading = false;
                    }

                    @Override
                    public void onError(Throwable error) {
                        products.onError(error);
                        isLoading = false;
                    }
                });
    }

    public boolean isLoading() {
        return isLoading;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean hasFetchedAll() {
        if (currentPageNumber*pageSize >= totalProducts) {
            return true;
        }
        return false;
    }
}
