package com.zhizhkin.andrey.internshiptask2;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.zhizhkin.andrey.internshiptask2.model.UserRequestsManager;

public class InternshipTask2Application extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        UserRequestsManager.initInstance();
    }

    public static Context getContext() {
        return mContext;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId);
        return result;
    }

    public static int getNavigationBarHeight() {
        int result = 0;
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId);
        return result;
    }

}