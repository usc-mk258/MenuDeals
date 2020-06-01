package com.application.app.myresturants.models;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantOrderResponse {


    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public ArrayList<OrdersModel> getData() {
        return data;
    }

    public void setData(ArrayList<OrdersModel> data) {
        this.data = data;
    }

    ArrayList<OrdersModel> data;
}
