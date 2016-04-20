package com.zhizhkin.andrey.internshiptask2;

import android.app.Application;
import android.content.Context;

import com.zhizhkin.andrey.internshiptask2.Model.UserRequestsManager;

public class InternshipTask2Application extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        UserRequestsManager.initInstance();
    }

    public static Context getContext(){
        return mContext;
    }
}