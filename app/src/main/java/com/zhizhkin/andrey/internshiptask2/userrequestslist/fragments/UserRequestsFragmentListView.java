package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestsListItemBinding;
import com.zhizhkin.andrey.internshiptask2.model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.model.UserRequestsManager;

public class UserRequestsFragmentListView extends UserRequestsFragment implements AdapterView.OnItemClickListener {

    public static UserRequestsFragment newInstance(UserRequest.StatusType userRequestStatusType) {
        UserRequestsFragmentListView fragment = new UserRequestsFragmentListView();
        fragment.mUserRequestsStatusType = userRequestStatusType;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.user_requests_list_fragment_listview, container, false);
        ListView requestsListView = (ListView) fragmentView.findViewById(R.id.requestsManagerListView);
        requestsListView.setAdapter(new ArrayAdapter<UserRequest>(this.getContext(), R.layout.user_requests_list_item, mUserRequests) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.user_requests_list_item, parent, false);
                ((UserRequestsListItemBinding) DataBindingUtil.bind(convertView)).setUserRequest(mUserRequests.get(position));
                return convertView;
            }

        });
        requestsListView.setOnItemClickListener(this);
        if (mCallback != null) mCallback.onListViewCreated(requestsListView);
        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserRequestsManager.startRequestViewerActivity(mUserRequests.get(position), view);
    }

}
