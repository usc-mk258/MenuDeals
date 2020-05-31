package com.application.app.myresturants.models;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrderResponse {


    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public HashMap<String, ArrayList<OrdersModel>> getData() {
        return data;
    }

    public void setData(HashMap<String, ArrayList<OrdersModel>> data) {
        this.data = data;
    }

    HashMap<String,ArrayList<OrdersModel>> data;
}
