package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.res.Resources;

import com.zhizhkin.andrey.internshiptask2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RequestsManager {
    private static RequestsManager requestsManager;
    private static List<UserRequest> mRequests;
    private UserRequest mCurrentRequest;

    private RequestsManager() {}

    public static void initInstance(Resources resources) {
        requestsManager = new RequestsManager();
        loadRequestsData(resources);
    }

    public static RequestsManager getInstance() {
        return requestsManager;
    }

    private static void loadRequestsData(Resources resources) {
        mRequests=new ArrayList<>();
        for(int i=0;i<=20;i++){
            mRequests.add(generateUserRequest(resources));
        }
        fillPictures(mRequests,resources);
        requestsManager.mCurrentRequest=mRequests.get(0);
    }

    private static UserRequest generateUserRequest(Resources resources) {
        Random random = new Random();
        UserRequest newUserRequest = new UserRequest();
        newUserRequest.setStatus(UserRequest.StatusType.values()[random.nextInt(UserRequest.StatusType.values().length)]);
        newUserRequest.setLikes(random.nextInt(100));
        String[] streetsArray = resources.getStringArray(R.array.streets_array);
        newUserRequest.setAddress(streetsArray[random.nextInt(streetsArray.length)]+", "+(random.nextInt(150)+1));
        Date startDate;
        switch (newUserRequest.getStatus()) {
            case DONE:
                startDate = getDate("01/03/16");
                break;
            default:
                startDate = getDate("01/04/16");
        }
        newUserRequest.setDateCreated(addRandomDays(startDate,30));
        newUserRequest.setRegistrationDate(addRandomDays(newUserRequest.getDateCreated(),3));
        newUserRequest.setDateSolveBy(addRandomDays(newUserRequest.getRegistrationDate(),30));
        return newUserRequest;
    }

    private static Date getDate(String strDate) {
        Date date=new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static void fillPictures(List<UserRequest> userRequests, Resources resources){

    }

    private  static Date addRandomDays(Date startDate, int interval){
        int addDays = new Random().nextInt(interval)+1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, addDays);
        return calendar.getTime();
    }

    public List<UserRequest> getRequests(UserRequest.StatusType status){
        List<UserRequest> filtered = new ArrayList<>();
        for (UserRequest ur:mRequests) if(ur.getStatus()==status)filtered.add(ur);
        return filtered;
    }

    public void setCurrent(UserRequest request){
        mCurrentRequest=request;
    }

    public UserRequest getCurrent(){
        return mCurrentRequest;
    }

}
