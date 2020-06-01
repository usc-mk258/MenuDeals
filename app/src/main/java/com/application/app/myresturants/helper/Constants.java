package com.application.app.myresturants.helper;

import android.util.Base64;
import android.util.Log;

import com.application.app.myresturants.models.CustomerToken;

import java.io.UnsupportedEncodingException;

public class Constants {
    public static final String FILENAME = "menu_preferences";
    public static final String AUTHENTICATE_USER_TOKEN = "user_token";
    public static final String DECODE_USER_TOKEN = "token";


    public static String decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            GsonHelper gsonHelper = new GsonHelper();
            return (getJson(split[1]));
         //   return gsonHelper.GsonToCustomerToekn(getJson(split[1]));

        } catch (UnsupportedEncodingException e) {
            //Error
        }
        return null;
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
