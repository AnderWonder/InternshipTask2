package com.zhizhkin.andrey.mystudytask2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import com.zhizhkin.andrey.mystudytask2.data.UserRequest;
import com.zhizhkin.andrey.mystudytask2.userrequestviewer.UserRequestViewerActivity;

public class MyStudy2Application extends Application {

    private static Context sContext;

    private static UserRequest sCurrentUserRequest;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        Resources resources = sContext.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId);
        return result;
    }

    public static int getNavigationBarHeight() {
        int result = 0;
        Resources resources = sContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId);
        return result;
    }

    public static void startRequestViewerActivity(UserRequest request, AppCompatActivity activity) {
        sCurrentUserRequest = request;
        activity.startActivity(new Intent(activity, UserRequestViewerActivity.class));
    }

    public static UserRequest getCurrentUserRequest() {
        return sCurrentUserRequest;
    }

}