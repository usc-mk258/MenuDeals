package com.application.app.myresturants;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.GsonHelper;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestauranResponse;
import com.application.app.myresturants.models.RestautantModel;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpAsCustomer extends AppCompatActivity {
    TextView getStart;
    private LoginResponse signUpResponsesData;
    GsonHelper gsonHelper;
    TextInputEditText name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_customer);
        this.getSupportActionBar().hide();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        getStart = (TextView) findViewById(R.id.get_start);
        gsonHelper = new GsonHelper();
        getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(name.getText().toString().equals("")
                        || email.getText().toString().equals("")
                        || password.getText().toString().equals(""))
                {
                    Toast.makeText(SignUpAsCustomer.this, "please fill all fields", Toast.LENGTH_SHORT).show();

                }
                else {
                    cusomerSignUp(name.getText().toString(),email.getText().toString(),password.getText().toString());
                }

            }
        });


    }





    private void cusomerSignUp(String name,String email,String password){
        final ProgressDialog progressDialog = new ProgressDialog(SignUpAsCustomer.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        HashMap<String, Object> jsonParams = new HashMap<>();
        //put something inside the map, could be null
        jsonParams.put("name", name);
        jsonParams.put("email", email);
        jsonParams.put("password", password);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


        Call<LoginResponse> response = Api.getClient().signUpCustomer( body);

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                //  String restautantModels = (String)signUpResponsesData.getData().get("restaurants");
                    Toast.makeText(SignUpAsCustomer.this,"Customer created", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {

                    try {
                        Toast.makeText(SignUpAsCustomer.this, gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();


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


















}
