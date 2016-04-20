package com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments;


import android.support.v4.app.Fragment;

import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.List;

public class RequestsFragment extends Fragment {

    protected List<UserRequest> mUserRequests;

    protected String mTitle;

    public RequestsFragment() {
        // Required empty public constructor
    }

    public void setRequests(List<UserRequest> requests) {
        mUserRequests = requests;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
