package com.zhizhkin.andrey.internshiptask2.Model;

import java.util.ArrayList;
import java.util.List;

public class UserRequestsManager {
    private static UserRequestsManager sUserRequestsManager;
    private static List<UserRequest> sUserRequests;
    private UserRequest mCurrentRequest;

    public static void initInstance() {
        sUserRequestsManager = new UserRequestsManager();
        loadRequestsData();
    }

    public static UserRequestsManager getInstance() {
        if (sUserRequestsManager == null) initInstance();
        return sUserRequestsManager;
    }

    private static void loadRequestsData() {
        sUserRequests = TestDataGenerator.generateTestData(10);
        sUserRequestsManager.setCurrent(sUserRequests.get(0));
    }

    public List<UserRequest> getRequestsWithStatus(UserRequest.StatusType status) {
        List<UserRequest> filtered = new ArrayList<>();
        for (UserRequest ur : sUserRequests) if (ur.getStatus() == status) filtered.add(ur);
        return filtered;
    }

    public UserRequest getCurrent() {
        return mCurrentRequest;
    }

    public void setCurrent(UserRequest request) {
        mCurrentRequest = request;
    }

}
