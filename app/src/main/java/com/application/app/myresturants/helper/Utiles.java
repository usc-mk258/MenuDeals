package com.application.app.myresturants.helper;

import android.app.job.JobScheduler;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.google.firebase.messaging.FirebaseMessaging.INSTANCE_ID_SCOPE;

public class Utiles {

public String dateFormater(String date) throws ParseException {
  //  String string = "January 2, 2010";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date dateFormated = format.parse(date);


    SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy");
  //  System.out.println("" + output.format(dateFormated) + " real date " + startTime);
return  output.format(dateFormated);
}


public void LogOutPreferences(Context context)  {


    Prefrences prefrences = new Prefrences();
    prefrences.removePreference(context,Constants.FILENAME,Constants.USER_PASSWORD);
    prefrences.removePreference(context,Constants.FILENAME,Constants.USER_ID);
    prefrences.removePreference(context,Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN);
    prefrences.removePreference(context,Constants.FILENAME,Constants.DECODE_USER_TOKEN);
    JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
    jobScheduler.cancelAll();





   // FirebaseInstanceId.getInstance().deleteInstanceId();

}


}
