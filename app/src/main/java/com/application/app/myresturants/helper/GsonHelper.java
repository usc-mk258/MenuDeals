package com.application.app.myresturants.helper;

import com.application.app.myresturants.models.CustomerToken;
import com.application.app.myresturants.models.JsonErrorModel;
import com.application.app.myresturants.models.RestautantModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonHelper
{


    public static ArrayList<RestautantModel> GsonToRestaurants(String recordsArrayString) {

        Type listType = new TypeToken<List<RestautantModel>>() {
        }.getType();
        return new Gson().fromJson(recordsArrayString, listType);
    }




    public static CustomerToken GsonToCustomerToekn(String recordsArrayString) {

        Type listType = new TypeToken<CustomerToken>() {
        }.getType();
        return new Gson().fromJson(recordsArrayString, listType);
    }


    public static JsonErrorModel GsonJsonError(String recordsArrayString) {

        Type listType = new TypeToken<JsonErrorModel>() {
        }.getType();
        return new Gson().fromJson(recordsArrayString, listType);
    }

}
