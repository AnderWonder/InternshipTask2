package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhizhkin.andrey.internshiptask2.RequestViewer.RequestViewerActivity;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.RequestsManagerActivity;

public class UserRequestViewModelBinder {
    private static Context sContext;
    private static String sPackageName;
    private static Resources sResources;
    private static String sPrefix;
    private static View sViewGroup;
    public static void Bind(View viewGroup, UserRequest userRequest) {
        sContext=viewGroup.getContext();
        sResources = sContext.getResources();
        sPackageName = sContext.getPackageName();
        sPrefix=viewGroup instanceof ScrollView ? "requestViewer" : "requestsManager";
        sViewGroup = viewGroup;

        setTextToTextView("StatusTextView",userRequest.getStatus().toString());
        setTextToTextView("RequestInfoTextView",userRequest.getRequestInfo());
        setTextToTextView("AddressTextView",userRequest.getAddress());
        setTextToTextView("LikesTextView",String.valueOf(userRequest.getLikes()));
        setTextToTextView("CreationDateTextView",android.text.format.DateFormat.getMediumDateFormat(sContext).format(userRequest.getDateCreated()));
        setTextToTextView("RegistrationDateTextView",android.text.format.DateFormat.getMediumDateFormat(sContext).format(userRequest.getRegistrationDate()));
        setTextToTextView("SolveDateTextView",android.text.format.DateFormat.getMediumDateFormat(sContext).format(userRequest.getDateSolveBy()));
        setTextToTextView("RequestDaysLeftTextView",String.valueOf(userRequest.getDaysLeft()));

    }

    public static void setTextToTextView(String textViewName, String text){
        TextView bindTextView = ((TextView) sViewGroup.findViewById(sResources.getIdentifier(sPrefix+textViewName, "id", sPackageName)));
        if(bindTextView!=null)bindTextView.setText(text);

    }
    public static void startRequestViewer(UserRequest request, View v) {
        RequestsManager.getInstance().setCurrent(request);
        RequestsManagerActivity act = ((RequestsManagerActivity) v.getContext());
        act.startActivity(new Intent(act, RequestViewerActivity.class));
    }
}
