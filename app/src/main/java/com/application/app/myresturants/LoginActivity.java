package com.application.app.myresturants;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Constants;
import com.application.app.myresturants.helper.GsonHelper;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.CustomerToken;
import com.application.app.myresturants.models.LoginResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
Button signup_restaurant,getSignup_customer,button3;
    TextInputEditText id,pass;
    LoginResponse signUpResponsesData;
GsonHelper gsonHelper;
    private static final String TAG = "abcdef";

    JobScheduler jobScheduler;
    private static final int MYJOBID = 1;
   // Chronometer chronometer;

    RadioGroup radioGroup;
    private RadioButton radioButton;
    String fireBaseToken;
    String  user_id,user_password;

     CustomerToken customerToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        Prefrences prefrences = new Prefrences();

         user_id =   prefrences.getUserIdPreference(LoginActivity.this);
         user_password =  prefrences.getUserPassPreference(LoginActivity.this);


         customerToken=  prefrences.getTokenCustomer(LoginActivity.this);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( LoginActivity.this,
                new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                fireBaseToken  = instanceIdResult.getToken();
                Log.e("newToken",fireBaseToken);






                progressDialog.dismiss();


                if(customerToken!=null)
                    if(customerToken.getRole()!=null && customerToken.getRole().equalsIgnoreCase("customer")){


                        signin(user_id,user_password,fireBaseToken);

                    }else if(customerToken.getRole()!=null && customerToken.getRole().equalsIgnoreCase("restaurant")) {
                        signUpRestaurant(user_id,user_password);

                    }




            }
        });







        gsonHelper = new GsonHelper();
        signup_restaurant = findViewById(R.id.button2);
        getSignup_customer = findViewById(R.id.button);
        button3 = findViewById(R.id.button3);

        id = findViewById(R.id.email);
        pass = findViewById(R.id.passsword);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(selectedId);
              //  Toast.makeText(LoginActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();

                if(id.getText().toString().isEmpty() && pass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                }
                else {

if(radioButton.getText().toString().equalsIgnoreCase("customer")){

    signin(id.getText().toString(),pass.getText().toString(),fireBaseToken);
}
else{
    signUpRestaurant(id.getText().toString(),pass.getText().toString());
}

                }
            }
        });
        signup_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });

        getSignup_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpAsCustomer.class);
                startActivity(i);
            }
        });
    }



private void signin(final String id, final String password, final String fireBaseToken){
    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
    progressDialog.setCancelable(false); // set cancelable to false
    progressDialog.setMessage("Please Wait"); // set message
    progressDialog.show();
    HashMap<String, Object> jsonParams = new HashMap<>();
    //put something inside the map, could be null
    jsonParams.put("email", id);
    jsonParams.put("password", password);
    jsonParams.put("mobile_token", fireBaseToken);

    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

    Call<LoginResponse> response = Api.getClient().signin( body);

    response.enqueue(new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            signUpResponsesData = response.body();
            if(response.code()==200 && signUpResponsesData.isSuccess()){
                Prefrences prefrences = new Prefrences();
                prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get("token").toString());
             Constants constants = new Constants();
                try {
                   String customerToken = constants.decoded(signUpResponsesData.getData().get("token").toString());



                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.DECODE_USER_TOKEN,customerToken);
                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.USER_ID,id);
                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.USER_PASSWORD,password);
                    Intent i = new Intent(LoginActivity.this, CustomerActivity.class);
                    updateLocation();
                    startActivity(i);
                    LoginActivity.this.finish();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {

                try {
                    Toast.makeText(getApplicationContext(), gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            progressDialog.dismiss();

        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            Log.d("response", t.getStackTrace().toString());
            Toast.makeText(getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }
    });


}



private void signUpRestaurant(final String id, final String password){
    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
    progressDialog.setCancelable(false); // set cancelable to false
    progressDialog.setMessage("Please Wait"); // set message
    progressDialog.show();
    HashMap<String, Object> jsonParams = new HashMap<>();
    //put something inside the map, could be null
    jsonParams.put("email", id);
    jsonParams.put("password", password);

    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


    Call<LoginResponse> response = Api.getClient().loginRestaurant( body);

    response.enqueue(new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            signUpResponsesData = response.body();
            if(response.code()==200 && signUpResponsesData.isSuccess()){
                Prefrences prefrences = new Prefrences();
                prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get("token").toString());
             Constants constants = new Constants();
                try {
                    String customerToken = constants.decoded(signUpResponsesData.getData().get("token").toString());
                    Boolean introAdded = (Boolean) signUpResponsesData.getData().get("introAdded");
                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.DECODE_USER_TOKEN,customerToken);
                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.USER_ID,id);
                    prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.USER_PASSWORD,password);
                    Intent i = new Intent(LoginActivity.this, RestaurantActivity.class);
                    i.putExtra("introAdded",introAdded);
                    startActivity(i);
                    LoginActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {

                try {
                    Toast.makeText(getApplicationContext(), gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            progressDialog.dismiss();

        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            Log.d("response", t.getStackTrace().toString());
            Toast.makeText(getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }
    });
    /*(new Callback<ResponseBody>()
    {
        @Override
        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
        {
            try
            {


               // Prefrences prefrences = new Prefrences();
             //   prefrences.putStringPreference(LoginActivity.this, Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN);
                //get your response....
               // Toast.makeText(getApplicationContext(), rawResponse.body().string(), Toast.LENGTH_SHORT).show();

                Type listType = new TypeToken<LoginResponse>() {
                }.getType();
                LoginResponse loginResponse =  new Gson().fromJson(rawResponse.body().string(), listType);
                progressDialog.dismiss();//   Log.d(TAG, "RetroFit2.0 :RetroGetLogin: " + rawResponse.body().string());
            }
            catch (Exception e)
            { progressDialog.dismiss();//
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable throwable)
        { progressDialog.dismiss();//
            // other stuff...
        }
    });*/

}
  /*  private void signIn() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error
        (Api.getClient().registration("asd")).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                Toast.makeText(getApplicationContext(), response.body().getData(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                progressDialog.dismiss();

            }
        });
    }
*/




    private void updateLocation(){
        ComponentName componentName = new ComponentName(this, UpdateLocationJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(12, componentName)
                .setPeriodic(10000)
                .build();



        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled!");
        } else {
            Log.d(TAG, "Job not scheduled");
        }




    }


}
