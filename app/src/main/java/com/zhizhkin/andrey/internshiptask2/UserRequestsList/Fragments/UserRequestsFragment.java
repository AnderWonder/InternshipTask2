package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;


import android.support.v4.app.Fragment;

import com.melnykov.fab.FloatingActionButton;

import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.List;

public class UserRequestsFragment extends Fragment {

    protected List<UserRequest> mUserRequests;

    protected String mTitle;

    protected FloatingActionButton mFab;

    public UserRequestsFragment() {
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

    public void setFab(FloatingActionButton mFab) {
        this.mFab = mFab;
    }
}