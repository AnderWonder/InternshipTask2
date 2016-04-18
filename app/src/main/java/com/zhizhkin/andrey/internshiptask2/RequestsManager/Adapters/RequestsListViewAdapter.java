package com.zhizhkin.andrey.internshiptask2.RequestsManager.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.UserRequest;

import java.util.List;

public class RequestsListViewAdapter extends BaseAdapter {

    private List<UserRequest> mUserRequests;

    public RequestsListViewAdapter(List<UserRequest> userRequests) {
        mUserRequests = userRequests;
    }

    @Override
    public int getCount() {
        return mUserRequests.size();
    }

    @Override
    public UserRequest getItem(int position) {
        return mUserRequests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (convertView == null) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.requests_manager_item, parent, false);
        }
        RequestsViewModelBinder.Bind(itemView, mUserRequests.get(position));
        return itemView;
    }

}
