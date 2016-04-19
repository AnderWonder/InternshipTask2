package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.RequestViewer.RequestViewerActivity;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.RequestsManagerActivity;

import static android.text.format.DateFormat.*;

public class UserRequestViewModelBinder {

    private static View sViewGroup;

    public static void Bind(View viewGroup, UserRequest userRequest) {
        sViewGroup = viewGroup;
        setTextToTextView(R.id.requestStatusTextView,userRequest.getStatus().toString());
        setTextToTextView(R.id.requestInfoTextView,userRequest.getRequestInfo());
        setTextToTextView(R.id.requestAddressTextView,userRequest.getAddress());
        setTextToTextView(R.id.requestLikesTextView,String.valueOf(userRequest.getLikes()));
        setTextToTextView(R.id.requestCreationDateTextView, getMediumDateFormat(viewGroup.getContext()).format(userRequest.getDateCreated()));
        setTextToTextView(R.id.requestRegistrationDateTextView, getMediumDateFormat(viewGroup.getContext()).format(userRequest.getRegistrationDate()));
        setTextToTextView(R.id.requestSolveDateTextView, getMediumDateFormat(viewGroup.getContext()).format(userRequest.getDateSolveBy()));
        setTextToTextView(R.id.requestDaysLeftTextView,String.valueOf(userRequest.getDaysLeft()));
        setTextToTextView(R.id.requestTypeTextView,userRequest.getType().toString());
        ImageView bindImageView = (ImageView) sViewGroup.findViewById(R.id.requestTypeImageView);
        if(bindImageView!=null)bindImageView.setImageResource(userRequest.getType().getIconId());
    }

    public static void setTextToTextView(int viewId, String text){
        TextView bindTextView = (TextView) sViewGroup.findViewById(viewId);
        if(bindTextView!=null)bindTextView.setText(text);
    }

    public static void startRequestViewer(UserRequest request, View v) {
        RequestsManager.getInstance().setCurrent(request);
        RequestsManagerActivity act = ((RequestsManagerActivity) v.getContext());
        act.startActivity(new Intent(act, RequestViewerActivity.class));
    }
}
