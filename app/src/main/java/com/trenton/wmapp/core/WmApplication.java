package com.trenton.wmapp.core;

import android.app.Application;

import com.trenton.wmapp.manager.RetrofitManager;

import timber.log.Timber;

public class WmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init(this);

        Timber.plant(new Timber.DebugTree());
    }
}
