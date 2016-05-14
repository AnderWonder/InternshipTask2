package com.zhizhkin.andrey.internshiptask2;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;

import com.zhizhkin.andrey.internshiptask2.data.UserRequest;
import com.zhizhkin.andrey.internshiptask2.userrequestslist.UserRequestsListActivity;
import com.zhizhkin.andrey.internshiptask2.userrequestviewer.UserRequestViewerActivity;

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

    public static void startRequestViewerActivity(UserRequest request, View view) {
        sCurrentUserRequest =request;
        UserRequestsListActivity activity = ((UserRequestsListActivity) view.getContext());
        activity.startActivity(new Intent(activity, UserRequestViewerActivity.class));
    }

    public static UserRequest getCurrentUserRequest() {
        return sCurrentUserRequest;
    }

}