package com.zhizhkin.andrey.internshiptask2.data;

import android.database.Cursor;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static java.text.DateFormat.*;

public class UserRequest {

    private static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
    private RequestType mType;
    private StatusType mStatus;
    private int mLikes;
    private Date mCreationDate;
    private Date mRegistrationDate;
    private Date mSolveDate;
    private String mRequestInfo;
    private String mAddress;
    private String mId;
    private String mResponsible;
    private List<Uri> mPictures;

    public UserRequest() {
    }

    ;

    public UserRequest(Cursor cursor) {
        mId = cursor.getString(cursor.getColumnIndex("id"));
        mLikes = cursor.getInt(cursor.getColumnIndex("likes"));
        mStatus = StatusType.getById(cursor.getInt(cursor.getColumnIndex("status")));
        mType = RequestType.getById(cursor.getInt(cursor.getColumnIndex("type")));
        mRequestInfo = cursor.getString(cursor.getColumnIndex("requestInfo"));
        mAddress = cursor.getString(cursor.getColumnIndex("address"));
        mResponsible = cursor.getString(cursor.getColumnIndex("responsible"));
        mCreationDate = getDate(cursor.getString(cursor.getColumnIndex("creationDate")));
        mRegistrationDate = getDate(cursor.getString(cursor.getColumnIndex("registrationDate")));
        mSolveDate = getDate(cursor.getString(cursor.getColumnIndex("solveDate")));
        mPictures = new ArrayList();
        String[] pictures = cursor.getString(cursor.getColumnIndex("pictures")).split(";");
        for (String picture : pictures)
            mPictures.add(Uri.parse(picture));
    }

    private Date getDate(String strDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public RequestType getType() {
        return mType;
    }

    public void setType(RequestType mRequestType) {
        this.mType = mRequestType;
    }

    public StatusType getStatus() {
        return mStatus;
    }

    public void setStatus(StatusType mStatus) {
        this.mStatus = mStatus;
    }

    public int getLikes() {
        return mLikes;
    }

    public void setLikes(int mLikes) {
        this.mLikes = mLikes;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public void setDateCreated(Date mCreationDate) {
        this.mCreationDate = mCreationDate;
    }

    public Date getRegistrationDate() {
        return mRegistrationDate;
    }

    public void setRegistrationDate(Date mRegistrationDate) {
        this.mRegistrationDate = mRegistrationDate;
    }

    public Date getSolveDate() {
        return mSolveDate;
    }

    public void setSolveDate(Date mSolveDate) {
        this.mSolveDate = mSolveDate;
    }

    public int getDaysLeft() {
        if (mSolveDate != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(mSolveDate);
            return (int) ((System.currentTimeMillis() - gc.getTimeInMillis()) / MILLIS_IN_DAY) + 1;
        }
        return 0;
    }

    public String getRequestInfo() {
        return mRequestInfo + " " + getAddress();
    }

    public void setRequestInfo(String mRequestInfo) {
        this.mRequestInfo = mRequestInfo;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getResponsible() {
        return mResponsible;
    }

    public void setResponsible(String mResponsible) {
        this.mResponsible = mResponsible;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public List<Uri> getPictures() {
        return mPictures;
    }

    public void setPictures(List<Uri> mPictures) {
        this.mPictures = mPictures;
    }

    public enum RequestType {
        TYPE1(R.string.user_request_type_1, R.drawable.ic_doc, 0),
        TYPE2(R.string.user_request_type_2, R.drawable.ic_trash, 1),
        TYPE3(R.string.user_request_type_3, R.drawable.ic_chart, 2),
        TYPE4(R.string.user_request_type_4, R.drawable.ic_elevator, 3);

        private int mTypeStringResourceId;
        private int mIcId;
        private int mTypeId;

        RequestType(int typeStringResourceId, int icId, int typeId) {
            mTypeStringResourceId = typeStringResourceId;
            mIcId = icId;
            mTypeId = typeId;
        }

        public int getIconId() {
            return mIcId;
        }

        public int getId() {
            return mTypeId;
        }

        @Override
        public String toString() {
            return InternshipTask2Application.getContext().getString(mTypeStringResourceId);
        }

        public static RequestType getById(int id){
            for (RequestType type : values())
                if (type.getId() == id) return type;
            return null;
        }
    }

    public enum StatusType {
        IN_PROCESS(R.string.user_request_status_type_1, 0), DONE(R.string.user_request_status_type_2, 1), WAITING(R.string.user_request_status_type_3, 2);

        private int mNameStringResourceId;

        private int mStatusId;

        StatusType(int nameStringResourceId, int loaderId) {
            mNameStringResourceId = nameStringResourceId;
            mStatusId = loaderId;
        }

        @Override
        public String toString() {
            return InternshipTask2Application.getContext().getString(mNameStringResourceId);
        }

        public int getId() {
            return mStatusId;
        }

        public static StatusType getById(int id){
            for (StatusType status : values())
                if (status.getId() == id) return status;
            return null;
        }
    }

    @BindingAdapter("bind:userRequestTypeIcon")
    public static void setIcon(ImageView imageView, int iconId) {
        imageView.setImageResource(iconId);
    }

    @BindingAdapter("bind:userRequestDate")
    public static void setSolveDate(TextView textView, Date date) {
        if (date != null)
            textView.setText(getDateInstance(MEDIUM, new Locale("uk", "UA")).format(date));
    }

}


