package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public class UserRequestsRecyclerViewFragment extends UserRequestsFragment {

    private UserRequestsRecyclerViewCursorAdapter mAdapter;

    public static UserRequestsRecyclerViewFragment newInstance(UserRequest.StatusType userRequestStatus) {
        UserRequestsRecyclerViewFragment fragment = new UserRequestsRecyclerViewFragment();
        fragment.mUserRequestsStatusType = userRequestStatus;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new UserRequestsRecyclerViewCursorAdapter(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.user_requests_list_fragment_recyclerview, container, false);
        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.requestsManagerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        if (mCallback != null) mCallback.onFragmentListViewCreated(recyclerView);
        return fragmentView;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
