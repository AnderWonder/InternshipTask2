package com.zhizhkin.andrey.internshiptask2.RequestsManager.Model;

import java.util.ArrayList;
import java.util.List;

public class RequestsManager {
    private static RequestsManager requestsManager;
    private static List<UserRequest> mRequests;
    private UserRequest mCurrentRequest;

    private RequestsManager() {}

    public static void initInstance() {
        requestsManager = new RequestsManager();
        loadRequestsData();
    }

    public static RequestsManager getInstance() {
        if (requestsManager == null) initInstance();
        return requestsManager;
    }

    private static void loadRequestsData() {
        mRequests=new ArrayList<>();
        mRequests.add(new UserRequest(UserRequest.StatusType.IN_PROCESS,"wesjdlfkjsdlfiuslfjsldfjsdl"));
        mRequests.add(new UserRequest(UserRequest.StatusType.IN_PROCESS,"23423423423423423"));
        mRequests.add(new UserRequest(UserRequest.StatusType.IN_PROCESS,"2342423rasfdsadfcsdfsd"));
        mRequests.add(new UserRequest(UserRequest.StatusType.IN_PROCESS,"324_________________"));
        mRequests.add(new UserRequest(UserRequest.StatusType.DONE,"QWEADASDFSAFSDFSDFGSDFSDF"));
        mRequests.add(new UserRequest(UserRequest.StatusType.DONE,"./././/./././............."));
        mRequests.add(new UserRequest(UserRequest.StatusType.DONE,"-------------------------"));
        mRequests.add(new UserRequest(UserRequest.StatusType.DONE,"777777777777777777777777777"));
        mRequests.add(new UserRequest(UserRequest.StatusType.WAITING,"WAITINGQWEADASDFSAFSDFSDFGSDFSDF"));
        mRequests.add(new UserRequest(UserRequest.StatusType.WAITING,"WAITING./././/./././............."));
        mRequests.add(new UserRequest(UserRequest.StatusType.WAITING,"WAITING-------------------------"));
        mRequests.add(new UserRequest(UserRequest.StatusType.WAITING,"WAITING777777777777777777777777777"));
    }

    public List<UserRequest> getRequests(UserRequest.StatusType status){
        List<UserRequest> filtered = new ArrayList<>();
        for (UserRequest ur:mRequests) if(ur.status==status)filtered.add(ur);
        return filtered;
    }

    public void setCurrent(UserRequest request){
        mCurrentRequest=request;
    }

    public UserRequest getCurrent(){
        return mCurrentRequest;
    }

}
