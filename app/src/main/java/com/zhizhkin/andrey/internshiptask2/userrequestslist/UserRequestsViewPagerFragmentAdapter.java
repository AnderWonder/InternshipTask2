package com.zhizhkin.andrey.internshiptask2.userrequestslist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;

import java.util.List;

public class UserRequestsViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Pair<String,Fragment>> mPages;

    public UserRequestsViewPagerFragmentAdapter(FragmentManager fm, List<Pair<String,Fragment>> pages) {
        super(fm);
        mPages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mPages.get(position).second;
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String)mPages.get(position).first;
    }
}
