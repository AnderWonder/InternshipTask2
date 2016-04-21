package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.UserRequestViewer.UserRequestViewerActivity;
import com.zhizhkin.andrey.internshiptask2.UserRequestsList.UserRequestsListActivity;

import static android.text.format.DateFormat.*;

public class UserRequestViewBinder {

    private static View sViewGroup;

    public static void Bind(View viewGroup, UserRequest userRequest) {
        sViewGroup = viewGroup;
        Context context = viewGroup.getContext();
        setTextToTextView(R.id.requestStatusTextView, userRequest.getStatus().toString());
        setTextToTextView(R.id.requestInfoTextView, userRequest.getRequestInfo()+" "+userRequest.getAddress());
        setTextToTextView(R.id.requestAddressTextView, userRequest.getAddress());
        setTextToTextView(R.id.requestLikesTextView, String.valueOf(userRequest.getLikes()));
        setTextToTextView(R.id.requestCreationDateTextView, getMediumDateFormat(context).format(userRequest.getDateCreated()));
        setTextToTextView(R.id.requestRegistrationDateTextView, getMediumDateFormat(context).format(userRequest.getRegistrationDate()));
        setTextToTextView(R.id.requestSolveDateTextView, getMediumDateFormat(context).format(userRequest.getDateToSolve()));
        setTextToTextView(R.id.requestDaysLeftTextView, String.valueOf(userRequest.getDaysLeft())+context.getString(R.string.days_left_suffix));
        setTextToTextView(R.id.requestTypeTextView, userRequest.getType().toString());
        setTextToTextView(R.id.requestResponsibleNameTextView, userRequest.getResponsible());
        ImageView bindImageView = (ImageView) sViewGroup.findViewById(R.id.requestTypeImageView);
        if (bindImageView != null)
            bindImageView.setImageResource(userRequest.getType().getIconId());
    }

    public static void setTextToTextView(int viewId, String text) {
        TextView bindTextView = (TextView) sViewGroup.findViewById(viewId);
        if (bindTextView != null) bindTextView.setText(text);
    }

    public static void startRequestViewerActivity(UserRequest request, View v) {
        UserRequestsManager.getInstance().setCurrent(request);
        UserRequestsListActivity act = ((UserRequestsListActivity) v.getContext());
        act.startActivity(new Intent(act, UserRequestViewerActivity.class));
    }
}
