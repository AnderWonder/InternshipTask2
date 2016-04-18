package com.zhizhkin.andrey.internshiptask2.RequestsManager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.UserRequest;

import java.util.List;

public class RequestsRecyclerViewAdapter extends RecyclerView.Adapter<RequestsRecyclerViewAdapter.ViewHolder> {

    private List<UserRequest> mUserRequests;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View mView;

        private UserRequest mUserRequest;

        public ViewHolder(View v) {
            super(v);
            mView=v;
            v.setOnClickListener(this);
        }

        public void setUserRequest(UserRequest request){
            mUserRequest=request;
        }

        @Override
        public void onClick(View v) {
            RequestsViewModelBinder.startRequestViewer(mUserRequest,v);
        }
    }

    public RequestsRecyclerViewAdapter(List<UserRequest> userRequests) {
        mUserRequests=userRequests;
    }

    @Override
    public RequestsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.requests_manager_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestsViewModelBinder.Bind(holder.mView, mUserRequests.get(position));
        holder.setUserRequest(mUserRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return mUserRequests.size();
    }

}