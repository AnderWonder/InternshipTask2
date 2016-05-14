package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public abstract class UserRequestsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    protected UserRequest.StatusType mUserRequestsStatusType;

    protected OnFragmentListViewCreatedListener mCallback;

    private LoaderManager mLoadManager;

    private Loader<Cursor> mCursorLoader;

    public interface OnFragmentListViewCreatedListener {
        void onFragmentListViewCreated(View listView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoadManager = getActivity().getSupportLoaderManager();
        mLoadManager.initLoader(mUserRequestsStatusType.getId(), null, this);
        try {
            mCallback = (OnFragmentListViewCreatedListener) context;
        } catch (ClassCastException e) {
            Log.e(toString(), context.toString() + " must implement OnFragmentListViewCreatedListener");
        }
    }

    public UserRequest.StatusType getUserRequestsStatusType() {
        return mUserRequestsStatusType;
    }

    public void setCursorLoader(CursorLoader newCursorLoader) {
        mCursorLoader = newCursorLoader;
        if (mLoadManager != null)
            mLoadManager.restartLoader(mUserRequestsStatusType.getId(), null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mCursorLoader;
    }

    @Override
    public abstract void onLoadFinished(Loader<Cursor> loader, Cursor data);

    @Override
    public abstract void onLoaderReset(Loader<Cursor> loader);

}
