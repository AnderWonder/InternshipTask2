package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.model.UserRequestsManager;

import java.util.List;

public abstract class UserRequestsFragment extends Fragment {

    protected List<UserRequest> mUserRequests;

    protected UserRequest.StatusType mUserRequestsStatusType;

    protected OnListViewCreatedListener mCallback;

    public interface OnListViewCreatedListener {
        void onListViewCreated(View listView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadUserRequests();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnListViewCreatedListener) context;
        } catch (ClassCastException e) {
            Log.e("Error", context.toString() + " must implement OnListViewCreatedListener");
        }
    }

    public UserRequest.StatusType getUserRequestsStatusType() {
        return mUserRequestsStatusType;
    }

    private void loadUserRequests() {
        mUserRequests = UserRequestsManager.getInstance().getRequestsWithStatus(mUserRequestsStatusType);
    }
}
