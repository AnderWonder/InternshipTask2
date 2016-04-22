package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.R;

public class UserRequestsFragmentRecyclerView extends UserRequestsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.user_requests_list_fragment_recyclerview, container, false);
        RecyclerView requestsRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.requestsManagerRecyclerView);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestsRecyclerView.setAdapter(new UserRequestsRecyclerViewAdapter(mUserRequests));
        initFab(fragmentView).attachToRecyclerView(requestsRecyclerView);
        return fragmentView;
    }

}
