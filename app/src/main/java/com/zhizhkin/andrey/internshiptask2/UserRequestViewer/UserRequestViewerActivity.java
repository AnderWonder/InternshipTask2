package com.zhizhkin.andrey.internshiptask2.userrequestviewer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.databinding.UserRequestViewerContentBinding;
import com.zhizhkin.andrey.internshiptask2.data.UserRequest;
import com.zhizhkin.andrey.internshiptask2.R;

public class UserRequestViewerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_request_viewer_activity);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        UserRequest userRequest = InternshipTask2Application.getCurrentUserRequest();
        setTitle(userRequest.getId());
        ((UserRequestViewerContentBinding) DataBindingUtil.bind(findViewById(R.id.userRequestViewerBindLayout))).setUserRequest(userRequest);

        createRecyclerView(new RecyclerViewPicassoUriAdapter(userRequest.getPictures(), this));
        setToastsToTextViewsInLayout((LinearLayout) findViewById(R.id.linearLayoutInScroll));
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

    private void setToastsToTextViewsInLayout(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof TextView) v.setOnClickListener(this);
            else if (v instanceof ViewGroup) setToastsToTextViewsInLayout((ViewGroup) v);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, getResources().getResourceEntryName(v.getId()), Toast.LENGTH_SHORT).show();
    }
}

