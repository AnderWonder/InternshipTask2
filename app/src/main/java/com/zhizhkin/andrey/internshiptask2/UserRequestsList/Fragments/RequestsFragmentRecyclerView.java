package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.zhizhkin.andrey.internshiptask2.R;

public class RequestsFragmentRecyclerView extends RequestsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_manager_fragment_recyclerview, container, false);
        RecyclerView requestsRecyclerView = (RecyclerView) view.findViewById(R.id.requestsManagerRecyclerView);
        requestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        requestsRecyclerView.setAdapter(new RequestsRecyclerViewAdapter(mUserRequests));
        ((FloatingActionButton) view.findViewById(R.id.fab)).attachToRecyclerView(requestsRecyclerView);
        return view;
    }

}
