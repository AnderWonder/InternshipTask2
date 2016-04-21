package com.zhizhkin.andrey.internshiptask2.UserRequestsList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhizhkin.andrey.internshiptask2.UserRequestsList.Fragments.UserRequestsFragment;

import java.util.List;

public class UserRequestsViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<UserRequestsFragment> mUserRequestsFragments;

    public UserRequestsViewPagerFragmentAdapter(FragmentManager fm, List<UserRequestsFragment> userRequestsFragments) {
        super(fm);
        mUserRequestsFragments = userRequestsFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mUserRequestsFragments.get(position);
    }

    @Override
    public int getCount() {
        return mUserRequestsFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mUserRequestsFragments.get(position).getTitle();
    }
}
