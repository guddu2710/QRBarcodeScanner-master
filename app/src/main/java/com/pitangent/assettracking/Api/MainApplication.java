package com.pitangent.assettracking.Api;

import android.app.Application;

import com.pitangent.assettracking.utils.SharedPreferencesManager;


public class MainApplication extends Application {
    public static ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.getInstance().init(this);
        apiManager = ApiManager.getInstance();
    }
}