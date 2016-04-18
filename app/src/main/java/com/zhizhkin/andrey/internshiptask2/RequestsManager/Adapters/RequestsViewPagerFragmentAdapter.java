package com.zhizhkin.andrey.internshiptask2.RequestsManager.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments.RequestsFragment;

import java.util.List;

public class RequestsViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<RequestsFragment> mRequestsFragments;

    public RequestsViewPagerFragmentAdapter(FragmentManager fm, List<RequestsFragment> requestsFragments) {
        super(fm);
        mRequestsFragments=requestsFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mRequestsFragments.get(position);
    }

    @Override
    public int getCount() {
        return mRequestsFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRequestsFragments.get(position).getTitle();
    }
}
