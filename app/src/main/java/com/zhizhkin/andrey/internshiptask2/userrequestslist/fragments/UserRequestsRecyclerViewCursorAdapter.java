package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestsListItemBinding;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public class UserRequestsRecyclerViewCursorAdapter extends RecyclerView.Adapter<UserRequestsRecyclerViewCursorAdapter.ViewHolder> {

    private Cursor mCursor;

    public UserRequestsRecyclerViewCursorAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @Override
    public UserRequestsRecyclerViewCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_requests_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        UserRequest userRequest=new UserRequest(mCursor);
        holder.setUserRequest(userRequest);
        ((UserRequestsListItemBinding) DataBindingUtil.bind(holder.mView)).setUserRequest(userRequest);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            InternshipTask2Application.startRequestViewerActivity(mUserRequest, v);
        }
    }

    public Cursor swapCursor(Cursor newCursor){
        Cursor oldCursor=null;
        if (newCursor != mCursor) {
            oldCursor=mCursor;
            mCursor=newCursor;
        }
        return oldCursor;
    }

}