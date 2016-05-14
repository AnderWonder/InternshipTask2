package com.zhizhkin.andrey.internshiptask2.userrequestviewer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zhizhkin.andrey.internshiptask2.MyStudy2Application;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestViewerContentBinding;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;
import com.zhizhkin.andrey.internshiptask2.R;

public class UserRequestViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_request_viewer_activity);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        UserRequest userRequest = MyStudy2Application.getCurrentUserRequest();
        setTitle(userRequest.getId());
        ((UserRequestViewerContentBinding) DataBindingUtil.bind(findViewById(R.id.userRequestViewerBindLayout))).setUserRequest(userRequest);

        createRecyclerView(new RecyclerViewPicassoUriAdapter(userRequest.getPictures()));
    }

    private void createRecyclerView(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.requestDescriptionRecyclerView);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

