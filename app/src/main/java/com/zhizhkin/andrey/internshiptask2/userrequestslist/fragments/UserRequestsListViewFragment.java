package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.zhizhkin.andrey.internshiptask2.MyStudy2Application;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestsListItemBinding;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public class UserRequestsListViewFragment extends UserRequestsFragment implements AdapterView.OnItemClickListener {

    private CursorAdapter mCursorAdapter;

    public static UserRequestsFragment newInstance(UserRequest.StatusType userRequestStatusType) {
        UserRequestsListViewFragment fragment = new UserRequestsListViewFragment();
        fragment.mUserRequestsStatusType = userRequestStatusType;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCursorAdapter = new CursorAdapter(getContext(), null, false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.user_requests_list_item, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                UserRequest userRequest = new UserRequest(cursor);
                ((UserRequestsListItemBinding) DataBindingUtil.bind(view)).setUserRequest(userRequest);
                view.setTag(userRequest);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.user_requests_list_fragment_listview, container, false);
        ListView listView = (ListView) fragmentView.findViewById(R.id.requestsManagerListView);
        listView.setAdapter(mCursorAdapter);
        listView.setOnItemClickListener(this);
        if (mCallback != null) mCallback.onFragmentListViewCreated(listView);
        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyStudy2Application.startRequestViewerActivity((UserRequest) view.getTag(), view);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}
