package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestsListItemBinding;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public class UserRequestsRecyclerViewCursorAdapter extends RecyclerView.Adapter<UserRequestsRecyclerViewCursorAdapter.ViewHolder> {

    public interface OnItemClickListener {
        public void onItemClick(ViewHolder holder);
    }

    private Cursor mCursor;

    private OnItemClickListener mOnItemClickListener;

    public UserRequestsRecyclerViewCursorAdapter(Cursor cursor, OnItemClickListener mOnItemClickListener) {
        mCursor = cursor;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public UserRequestsRecyclerViewCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_requests_list_item, parent, false),this);
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

    public Cursor swapCursor(Cursor newCursor){
        Cursor oldCursor=null;
        if (newCursor != mCursor) {
            oldCursor=mCursor;
            mCursor=newCursor;
        }
        return oldCursor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View mView;
        private UserRequest mUserRequest;
        private UserRequestsRecyclerViewCursorAdapter mOwner;

        public ViewHolder(View v, UserRequestsRecyclerViewCursorAdapter owner) {
            super(v);
            mView = v;
            mOwner = owner;
            v.setOnClickListener(this);
        }

        public UserRequest getUserRequest() {
            return mUserRequest;
        }

        public void setUserRequest(UserRequest request) {
            mUserRequest = request;
        }

        @Override
        public void onClick(View v) {
            mOwner.mOnItemClickListener.onItemClick(this);
        }

    }

}