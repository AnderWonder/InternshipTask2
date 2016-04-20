package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.res.Resources;
import android.net.Uri;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.zhizhkin.andrey.internshiptask2.R;

public class RequestsManager {
    private static RequestsManager sRequestsManager;
    private static List<UserRequest> sRequests;
    private UserRequest mCurrentRequest;

    public static void initInstance() {
        sRequestsManager = new RequestsManager();
        loadRequestsData();
    }

    public static RequestsManager getInstance() {
        if(sRequestsManager==null)initInstance();
        return sRequestsManager;
    }

    private static void loadRequestsData() {
        sRequests=TestDataGenerator.generateTestData(10);
        sRequestsManager.setCurrent(sRequests.get(0));
    }

    public List<UserRequest> getRequestsWithStatus(UserRequest.StatusType status) {
        List<UserRequest> filtered = new ArrayList<>();
        for (UserRequest ur : sRequests) if (ur.getStatus() == status) filtered.add(ur);
        return filtered;
    }

    public UserRequest getCurrent() {
        return mCurrentRequest;
    }

    public void setCurrent(UserRequest request) {
        mCurrentRequest = request;
    }

}
