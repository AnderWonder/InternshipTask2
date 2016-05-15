package com.zhizhkin.andrey.internshiptask2.userrequestslist.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhizhkin.andrey.internshiptask2.MyStudy2Application;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;

public class UserRequestsRecyclerViewFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, UserRequestsRecyclerViewCursorAdapter.OnItemClickListener {

    public interface OnFragmentListViewCreatedListener {
        void onFragmentListViewCreated(View listView);
    }

    private UserRequest.StatusType mUserRequestsStatusType;

    private OnFragmentListViewCreatedListener mCallback;

    private LoaderManager mLoadManager;

    private Loader<Cursor> mCursorLoader;

    private UserRequestsRecyclerViewCursorAdapter mAdapter;

    public static UserRequestsRecyclerViewFragment newInstance(UserRequest.StatusType userRequestStatus) {
        UserRequestsRecyclerViewFragment fragment = new UserRequestsRecyclerViewFragment();
        fragment.mUserRequestsStatusType = userRequestStatus;
        return fragment;
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
    public void onItemClick(UserRequestsRecyclerViewCursorAdapter.ViewHolder holder) {
        MyStudy2Application.startRequestViewerActivity(holder.getUserRequest(), (AppCompatActivity) getActivity());
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new UserRequestsRecyclerViewCursorAdapter(null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.user_requests_list_recyclerview_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.requestsManagerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        if (mCallback != null) mCallback.onFragmentListViewCreated(recyclerView);
        return fragmentView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mCursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
