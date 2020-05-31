package com.application.app.myresturants.models;

import java.util.ArrayList;

public class DealResponse {

    boolean success;
    ArrayList<DealsModel> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DealsModel> getData() {
        return data;
    }

    public void setData(ArrayList<DealsModel> data) {
        this.data = data;
    }
}
