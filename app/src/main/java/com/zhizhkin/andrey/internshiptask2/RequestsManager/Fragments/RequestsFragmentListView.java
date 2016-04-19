package com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestViewModelBinder;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

public class RequestsFragmentListView extends RequestsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests_manager_fragment_listview, container, false);
        ListView requestsListView = (ListView) view.findViewById(R.id.requestsManagerListView);
        requestsListView.setAdapter(new ArrayAdapter<UserRequest>(this.getContext(),R.layout.requests_manager_item,mUserRequests){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = convertView;
                if (itemView == null) {
                    itemView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.requests_manager_item, parent, false);
                }
                UserRequestViewModelBinder.Bind(itemView, mUserRequests.get(position));
                return itemView;

            }

        });
        requestsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserRequestViewModelBinder.startRequestViewer(mUserRequests.get(position),view);
            }
        });

        return view;
    }

}
