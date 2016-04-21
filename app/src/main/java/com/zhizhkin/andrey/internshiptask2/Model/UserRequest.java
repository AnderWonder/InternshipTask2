package com.zhizhkin.andrey.internshiptask2.Model;

import android.net.Uri;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.R;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserRequest {

    public static final int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
    private RequestType mType;
    private StatusType mStatus;
    private int mLikes;
    private Date mCreationDate;
    private Date mRegistrationDate;
    private Date mDateToSolve;
    private String mRequestInfo;
    private String mAddress;
    private String mId;
    private String mResponsible;
    private List<Uri> mPictures;

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

    public Date getDateCreated() {
        return mCreationDate;
    }

    public void setDateCreated(Date mDateCreated) {
        this.mCreationDate = mDateCreated;
    }

    public Date getRegistrationDate() {
        return mRegistrationDate;
    }

    public void setRegistrationDate(Date mRegistrationDate) {
        this.mRegistrationDate = mRegistrationDate;
    }

    public Date getDateToSolve() {
        return mDateToSolve;
    }

    public void setDateToSolve(Date mDateSolveBy) {
        this.mDateToSolve = mDateSolveBy;
    }

    public int getDaysLeft() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(mDateToSolve);
        return (int) ((new GregorianCalendar().getTimeInMillis() - gc.getTimeInMillis()) / MILLIS_IN_DAY);
    }

    public String getRequestInfo() {
        return mRequestInfo;
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
        TYPE1(R.string.user_request_type_1, R.drawable.ic_doc),
        TYPE2(R.string.user_request_type_2, R.drawable.ic_trash),
        TYPE3(R.string.user_request_type_3, R.drawable.ic_chart),
        TYPE4(R.string.user_request_type_4, R.drawable.ic_elevator);

        private String mName;
        private int mTypeStringResourceId;
        private int mIcId;

        RequestType(int typeStringResourceId, int icId) {
            mTypeStringResourceId = typeStringResourceId;
            mIcId = icId;
        }

        public int getIconId() {
            return mIcId;
        }

        @Override
        public String toString() {
            return InternshipTask2Application.getContext().getString(mTypeStringResourceId);
        }
    }

    public enum StatusType {
        IN_PROCESS(R.string.user_request_status_type_1), DONE(R.string.user_request_status_type_2), WAITING(R.string.user_request_status_type_3);

        private int mNameStringResourceId;

        StatusType(int nameStringResourceId) {
            mNameStringResourceId = nameStringResourceId;
        }

        @Override
        public String toString() {
            return InternshipTask2Application.getContext().getString(mNameStringResourceId);
        }
    }

}


