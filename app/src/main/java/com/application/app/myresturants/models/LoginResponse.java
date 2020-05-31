package com.application.app.myresturants.models;

import java.util.HashMap;

public class LoginResponse {


    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    HashMap<String,Object> data;
}
