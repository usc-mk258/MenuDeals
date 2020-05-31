package com.application.app.myresturants.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.application.app.myresturants.LoginActivity;

public class Prefrences {







    public void putStringPreference(Context context, String prefsName,
                                    String key, String value) {

        SharedPreferences preferences = context.getSharedPreferences(prefsName,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(key, value);
        editor.apply();
    }
public String getStringPreference(Context context){
    return getStringPreference(context,Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN);

}
    protected String getStringPreference(Context context, String prefsName,
                                         String key) {

        SharedPreferences preferences = context.getSharedPreferences(prefsName,
                Activity.MODE_PRIVATE);
        return preferences.getString(key, "");
    }






}
