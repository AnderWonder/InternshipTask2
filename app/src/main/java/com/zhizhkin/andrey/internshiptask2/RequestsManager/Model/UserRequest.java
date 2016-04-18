package com.zhizhkin.andrey.internshiptask2.RequestsManager.Model;

public class UserRequest {

    public String requestInfo;

    public StatusType status;

    public UserRequest(StatusType status,String requestInfo){
        this.requestInfo=requestInfo;
        this.status=status;
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


