package com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments;


import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import com.melnykov.fab.FloatingActionButton;
import com.zhizhkin.andrey.internshiptask2.R;

import java.util.List;

public class UserRequestsFragment extends Fragment {

    protected List<UserRequest> mUserRequests;

    protected String mTitle;

    public UserRequestsFragment() {
        // Required empty public constructor
    }

    public void setRequests(List<UserRequest> requests) {
        mUserRequests = requests;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    protected int getNavigationBarSize(){
        int result=0;
        Resources resources = InternshipTask2Application.getContext().getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = resources.getDimensionPixelSize(resourceId);
        return result;
    }

    protected FloatingActionButton initFab(View view){
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        //Shift for Translucent theme (Lollipop+ only)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CoordinatorLayout.LayoutParams llp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabMargin = (int)getResources().getDimension(R.dimen.fab_margin);
            llp.setMargins(0, 0, fabMargin, fabMargin + getNavigationBarSize());
            fab.setLayoutParams(llp);
        }
        return fab;
    }

}
