package com.zhizhkin.andrey.internshiptask2.Model;

import android.net.Uri;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserRequest {

    private RequestType mRequestType;
    private StatusType mStatus;
    private int mLikes;
    private Date mDateCreated;
    private Date mRegistrationDate;
    private Date mDateSolveBy;
    private String mRequestInfo;
    private String mAddress;
    private String mResponsible;
    private List<Uri> mPictures;

    public UserRequest(){
    }

    public UserRequest(StatusType status,String requestInfo, String address, Date dateCreated, Date dateRegistered,Date dateSolveBy){
        mStatus=status;
        mRequestInfo=requestInfo;
        mAddress=address;
        mDateCreated=dateCreated;
        mRegistrationDate =dateRegistered;
        mDateSolveBy=dateSolveBy;
    }

    public RequestType getRequestType() {
        return mRequestType;
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
        return mDateCreated;
    }

    public void setDateCreated(Date mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    public Date getRegistrationDate() {
        return mRegistrationDate;
    }

    public void setRegistrationDate(Date mRegistrationDate) {
        this.mRegistrationDate = mRegistrationDate;
    }

    public Date getDateSolveBy() {
        return mDateSolveBy;
    }

    public void setDateSolveBy(Date mDateSolveBy) {
        this.mDateSolveBy = mDateSolveBy;
    }

    public int getDaysLeft() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(mDateSolveBy);
        return (int)((new GregorianCalendar().getTimeInMillis()-gc.getTimeInMillis())/(1000 * 60 * 60 * 24));
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

    public List<Uri> getPictures() {
        return mPictures;
    }


    public enum RequestType {DISMANTING("Демонтаж in"),DONE("Done"),WAITING("Waiting");

        private String mName;
        RequestType(String name) {
            mName=name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    public enum StatusType {IN_PROCESS("In process"),DONE("Done"),WAITING("Waiting");

        private String mName;
        StatusType(String name) {
            mName=name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }
}


