package com.zhizhkin.andrey.internshiptask2.RequestViewer;

import android.net.Uri;
import android.os.Bundle;
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

import com.zhizhkin.andrey.internshiptask2.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestViewerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_viewer_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createRecyclerView(new RecyclerViewPicassoUriAdapter(getUriListFromAssetsPictures(), this));
        setToastsToTextViewsInLayout((LinearLayout) findViewById(R.id.linearLayoutInScroll));
    }

    private void createRecyclerView(RecyclerView.Adapter adapter) {
        RecyclerView mRecyclerView;
        mRecyclerView = (RecyclerView) findViewById(R.id.requestDescriptionRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    private void setToastsToTextViewsInLayout(ViewGroup VG) {
        int childcount = VG.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = VG.getChildAt(i);
            if (v instanceof TextView) v.setOnClickListener(this);
            else if (v instanceof ViewGroup) setToastsToTextViewsInLayout((ViewGroup) v);
        }
    }

    private List<Uri> getUriListFromAssetsPictures() {
        List<Uri> uriArrayList = new ArrayList();
        String picturesFolder = getString(R.string.request_viewer_pictures_assets_folder);
        try {
            for (String filename : getAssets().list(picturesFolder))
                uriArrayList.add(Uri.parse(getString(R.string.request_viewer_uri_part_assets) + picturesFolder + '/' + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uriArrayList;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, getResources().getResourceEntryName(v.getId()), Toast.LENGTH_SHORT).show();
    }
}

