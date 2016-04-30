package com.zhizhkin.andrey.internshiptask2.userrequestviewer;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhizhkin.andrey.internshiptask2.R;

import java.util.List;

class RecyclerViewPicassoUriAdapter extends RecyclerView.Adapter<RecyclerViewPicassoUriAdapter.ViewHolder> {

    private List<Uri> mUriList;

    private int mImageWidth;

    private UserRequestViewerActivity mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ViewHolder(View v, RecyclerViewPicassoUriAdapter owner) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.requestDescriptionImageView);
            mImageView.setOnClickListener(owner.mActivity);
        }

    }

    public RecyclerViewPicassoUriAdapter(List<Uri> UriList, UserRequestViewerActivity activity) {
        mUriList = UriList;
        mActivity = activity;
    }

    @Override
    public RecyclerViewPicassoUriAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        mImageWidth = (int) (displayMetrics.widthPixels - resources.getDimension(R.dimen.user_request_viewer_activity_horizontal_margin) * 2);
        if (resources.getInteger(R.integer.user_request_viewer_pictures_per_screen) != 0)
            mImageWidth /= resources.getInteger(R.integer.user_request_viewer_pictures_per_screen);
        mImageWidth -= resources.getDimension(R.dimen.user_request_viewer_image_item_margin_right);

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_request_viewer_image_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.mImageView.getContext())
                .load(mUriList.get(position))
                .resize(mImageWidth, 0)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mUriList.size();
    }

}