package com.zhizhkin.andrey.internshiptask2.Model;

import android.net.Uri;

import com.zhizhkin.andrey.internshiptask2.R;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserRequest {



    private RequestType mType;
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


    public enum RequestType {   TYPE1("Демонтаж інших об’єктів, що входять до переліку мал... in", R.drawable.ic_menu_camera),
                                TYPE2("Прибирання та санітарній стан території", R.drawable.ic_menu_gallery),
                                TYPE3("Питання стосовно нарахування боргу електрое...", R.drawable.ic_menu_manage),
                                TYPE4("Ремонт та обслуговування ліфтів", R.drawable.ic_menu_share);

        private String mName;
        private int mIcId;
        RequestType(String name, int icId) {
            mName=name;
            mIcId=icId;
        }

        public int getIconId(){
            return mIcId;
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


