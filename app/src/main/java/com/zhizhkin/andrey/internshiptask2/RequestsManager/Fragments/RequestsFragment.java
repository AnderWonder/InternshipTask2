package com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments;


import android.support.v4.app.Fragment;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.UserRequest;

import java.util.List;

public class RequestsFragment extends Fragment {

    protected List<UserRequest> mUserRequests;

    protected String mTitle;

    public RequestsFragment() {
        // Required empty public constructor
    }

    public RequestsFragment setRequests(List<UserRequest> requests){
        mUserRequests = requests;
        return this;
    }

    public RequestsFragment setTitle(String title){
        mTitle=title;
        return this;
    }

    public String getTitle(){
        return mTitle;
    }

}
