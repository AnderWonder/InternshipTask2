package com.zhizhkin.andrey.internshiptask2.UserRequestViewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;
import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestsManager;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequestViewBinder;

public class UserRequestViewerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_request_viewer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UserRequest userRequest = UserRequestsManager.getInstance().getCurrent();
        UserRequestViewBinder.Bind(findViewById(R.id.requestViewerScrollView), userRequest);
        setTitle(userRequest.getId());

        createRecyclerView(new RecyclerViewPicassoUriAdapter(userRequest.getPictures(), this));
        setToastsToTextViewsInLayout((LinearLayout) findViewById(R.id.linearLayoutInScroll));
    }

    private void createRecyclerView(RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView; //[Comment] BAD BAD. Make this in one row.
        mRecyclerView = (RecyclerView) findViewById(R.id.requestDescriptionRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    private void setToastsToTextViewsInLayout(ViewGroup VG) {
        int childcount = VG.getChildCount(); //[Comment] Use camel case
        for (int i = 0; i < childcount; i++) {
            View v = VG.getChildAt(i);
            if (v instanceof TextView) v.setOnClickListener(this);
            else if (v instanceof ViewGroup) setToastsToTextViewsInLayout((ViewGroup) v);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, getResources().getResourceEntryName(v.getId()), Toast.LENGTH_SHORT).show();
    }
}

