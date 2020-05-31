package com.application.app.myresturants.models;

import java.util.ArrayList;
import java.util.HashMap;

public class ReviewResponse {


    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RestaurantReviewModel getData() {
        return data;
    }

    public void setData(RestaurantReviewModel data) {
        this.data = data;
    }

    RestaurantReviewModel data;
}
