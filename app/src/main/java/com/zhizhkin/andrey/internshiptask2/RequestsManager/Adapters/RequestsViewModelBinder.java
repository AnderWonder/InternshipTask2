package com.zhizhkin.andrey.internshiptask2.RequestsManager.Adapters;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.RequestViewer.RequestViewerActivity;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.RequestsManager;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.RequestsManagerActivity;


public class RequestsViewModelBinder {
    public static void Bind(View view, UserRequest userRequest) {
        ((TextView) view.findViewById(R.id.request_info_text_view)).setText(userRequest.requestInfo);
    }

    public static void startRequestViewer(UserRequest request, View v) {
        RequestsManager.getInstance().setCurrent(request);
        RequestsManagerActivity act = ((RequestsManagerActivity) v.getContext());
        act.startActivity(new Intent(act, RequestViewerActivity.class));
    }
}
