package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestViewBinder;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

public class UserRequestsFragmentListView extends UserRequestsFragment implements AdapterView.OnItemClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.user_requests_list_fragment_listview, container, false);
        ListView requestsListView = (ListView) fragmentView.findViewById(R.id.requestsManagerListView);
        requestsListView.setAdapter(new ArrayAdapter<UserRequest>(this.getContext(), R.layout.user_requests_list_item, mUserRequests) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = convertView;
                if (itemView == null) {
                    itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.user_requests_list_item, parent, false);
                }
                UserRequestViewBinder.Bind(itemView, mUserRequests.get(position));
                return itemView;
            }

        });
        requestsListView.setOnItemClickListener(this);
        initFab(fragmentView).attachToListView(requestsListView);
        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserRequestViewBinder.startRequestViewerActivity(mUserRequests.get(position), view);
    }
}
