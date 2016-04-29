package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;


import android.support.v4.app.Fragment;

import com.melnykov.fab.FloatingActionButton;

import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.List;

public class UserRequestsFragment extends Fragment { //[Comment] Unnecessary Fragment

    protected List<UserRequest> mUserRequests;

    private String mTitle;

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

    public void setFab(FloatingActionButton mFab) { //[Comment] Wrong argument name
        this.mFab = mFab;
    } //[Comment] Fragment should not know about fab
}
