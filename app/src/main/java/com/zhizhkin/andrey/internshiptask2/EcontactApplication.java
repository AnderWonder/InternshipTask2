package com.zhizhkin.andrey.internshiptask2;

import android.app.Application;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.RequestsManager;

public class EcontactApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestsManager.initInstance();
    }
}