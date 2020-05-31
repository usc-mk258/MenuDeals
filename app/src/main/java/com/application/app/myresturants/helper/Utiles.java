package com.application.app.myresturants.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utiles {

public String dateFormater(String date) throws ParseException {
  //  String string = "January 2, 2010";
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date dateFormated = format.parse(date);


    SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy");
  //  System.out.println("" + output.format(dateFormated) + " real date " + startTime);
return  output.format(dateFormated);
}



}
