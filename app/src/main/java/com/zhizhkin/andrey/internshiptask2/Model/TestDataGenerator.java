package com.zhizhkin.andrey.internshiptask2.Model;

import android.content.res.Resources;
import android.net.Uri;

import com.zhizhkin.andrey.internshiptask2.InternshipTask2Application;
import com.zhizhkin.andrey.internshiptask2.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

class TestDataGenerator {

    public static List<UserRequest> generateTestData(int amount) {
        Resources resources = InternshipTask2Application.getContext().getResources();
        List<UserRequest> requests = new ArrayList<>();
        for (UserRequest.StatusType status : UserRequest.StatusType.values()) {
            for (int i = 0; i < amount; i++) {
                requests.add(generateUserRequest(resources, status));
            }
        }
        fillPictures(requests, resources);
        return requests;
    }

    private static UserRequest generateUserRequest(Resources resources, UserRequest.StatusType status) {
        Random random = new Random();
        UserRequest newUserRequest = new UserRequest();
        newUserRequest.setType(UserRequest.RequestType.values()[random.nextInt(UserRequest.RequestType.values().length)]);
        newUserRequest.setStatus(status);
        newUserRequest.setId(resources.getString(R.string.user_request_id_prefix) + String.valueOf(1000000 + random.nextInt(1000000)));
        newUserRequest.setLikes(random.nextInt(100));
        String[] streetsArray = resources.getStringArray(R.array.streets_array);
        newUserRequest.setAddress(streetsArray[random.nextInt(streetsArray.length)] + ", " + (1 + random.nextInt(150)));
        String[] requestInfoArray = resources.getStringArray(R.array.requests_info);
        newUserRequest.setRequestInfo(requestInfoArray[random.nextInt(requestInfoArray.length)]);
        String[] responciblesArray = resources.getStringArray(R.array.responcibles);
        newUserRequest.setResponsible(responciblesArray[random.nextInt(responciblesArray.length)]);
        Date startDate;
        switch (newUserRequest.getStatus()) {
            case DONE:
                startDate = getDate("01/02/16");
                break;
            default:
                startDate = getDate("01/04/16");
        }
        newUserRequest.setDateCreated(addRandomDays(startDate, 0, 15));
        newUserRequest.setRegistrationDate(addRandomDays(newUserRequest.getDateCreated(), 1, 3));
        newUserRequest.setDateToSolve(addRandomDays(newUserRequest.getRegistrationDate(), 25, 45));
        return newUserRequest;
    }

    private static Date getDate(String strDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static void fillPictures(List<UserRequest> userRequests, Resources resources) {
        List<Uri> uriArrayList = new ArrayList();
        String picturesFolder = resources.getString(R.string.request_viewer_pictures_assets_folder);
        try {
            for (String filename : resources.getAssets().list(picturesFolder))
                uriArrayList.add(Uri.parse(resources.getString(R.string.request_viewer_uri_part_assets) + picturesFolder + '/' + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int requestsCounter = 0;
        int picturesPerRequest = 3;
        for (UserRequest request : userRequests) {
            ArrayList<Uri> requestPictures = new ArrayList<>();
            int i = 0, restart = 0;
            for (; i < picturesPerRequest && i < uriArrayList.size(); i++) {
                if (requestsCounter + i == uriArrayList.size()) {
                    requestsCounter = 0;
                    restart = i;
                }
                requestPictures.add(uriArrayList.get(requestsCounter + (i - restart)));
            }
            request.setPictures(requestPictures);
            requestsCounter += i - restart;
        }

    }

    private static Date addRandomDays(Date startDate, int from, int to) {
        int addDays = new Random().nextInt(to) + from;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, addDays);
        return calendar.getTime();
    }

}
