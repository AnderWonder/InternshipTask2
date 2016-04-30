package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestsListItemBinding;
import com.zhizhkin.andrey.internshiptask2.model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.model.UserRequestsManager;

import java.util.List;

public class UserRequestsRecyclerViewAdapter extends RecyclerView.Adapter<UserRequestsRecyclerViewAdapter.ViewHolder> {

    private List<UserRequest> mUserRequests;

    public UserRequestsRecyclerViewAdapter(List<UserRequest> userRequests) {
        mUserRequests = userRequests;
    }

    @Override
    public UserRequestsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_requests_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((UserRequestsListItemBinding) DataBindingUtil.bind(holder.mView)).setUserRequest(mUserRequests.get(position));
        holder.setUserRequest(mUserRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserRequests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View mView;

        private UserRequest mUserRequest;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            v.setOnClickListener(this);
        }

        public void setUserRequest(UserRequest request) {
            mUserRequest = request;
        }

        @Override
        public void onClick(View v) {
            UserRequestsManager.startRequestViewerActivity(mUserRequest, v);
        }
    }

}