package com.zhizhkin.andrey.internshiptask2.model;

import android.content.Intent;
import android.view.View;

import com.zhizhkin.andrey.internshiptask2.userrequestslist.UserRequestsListActivity;
import com.zhizhkin.andrey.internshiptask2.userrequestviewer.UserRequestViewerActivity;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsManager {
    private static UserRequestsManager sUserRequestsManager;
    private List<UserRequest> mUserRequests;
    private UserRequest mCurrentRequest;

    public static void initInstance() {
        sUserRequestsManager = new UserRequestsManager();
        sUserRequestsManager.loadRequestsData();
    }

    public static UserRequestsManager getInstance() {
        if (sUserRequestsManager == null) initInstance();
        return sUserRequestsManager;
    }

    private void loadRequestsData() {
        mUserRequests = TestDataGenerator.generateTestData(10);
        sUserRequestsManager.setCurrentUserRequest(mUserRequests.get(0));
    }

    public List<UserRequest> getRequestsWithStatus(UserRequest.StatusType status) {
        List<UserRequest> filtered = new ArrayList<>();
        for (UserRequest ur : mUserRequests) if (ur.getStatus() == status) filtered.add(ur);
        return filtered;
    }

    public UserRequest getCurrentUserRequest() {
        return mCurrentRequest;
    }

    private void setCurrentUserRequest(UserRequest request) {
        mCurrentRequest = request;
    }

    public static void startRequestViewerActivity(UserRequest request, View view) {
        sUserRequestsManager.setCurrentUserRequest(request);
        UserRequestsListActivity activity = ((UserRequestsListActivity) view.getContext());
        activity.startActivity(new Intent(activity, UserRequestViewerActivity.class));
    }
}
