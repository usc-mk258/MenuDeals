package com.application.app.myresturants.models;

import java.util.ArrayList;
import java.util.HashMap;

public class RestauranResponse {


    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public HashMap<String, ArrayList<RestautantModel>> getData() {
        return data;
    }

    public void setData(HashMap<String, ArrayList<RestautantModel>> data) {
        this.data = data;
    }

    HashMap<String,ArrayList<RestautantModel>> data;
}
