package com.trenton.wmapp.manager;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// For a more robust project, I suggest using Dagger 2 to have a singleton.

public class RetrofitManager {

    private static final String BASE_URL = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/";
    private static long SIZE_OF_CACHE = 32 * 1024 * 1024;

    private Retrofit retrofit;

    private RetrofitManager(){}

    public void init(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), SIZE_OF_CACHE))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();
    }

    private static class SingletonHelper {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public Retrofit getDefaultRetrofitInstance() {
        return retrofit;
    }
}
