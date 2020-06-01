package com.application.app.myresturants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.GsonHelper;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class AddDeals extends AppCompatActivity {
ImageButton imageBtn;
Button submit;

    private LoginResponse signUpResponsesData;
    GsonHelper gsonHelper;
    private static final int CAMERA_REQUEST = 1888;
   DealsModel newDeal;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    TextInputEditText priceText,descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deals);
        gsonHelper = new GsonHelper();
        imageBtn = findViewById(R.id.imageButton);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
newDeal = new DealsModel();
                priceText = findViewById(R.id.price_text);
                descText = findViewById(R.id.desc_text);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }




            }
        });


        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(priceText.getText().toString().equalsIgnoreCase("")  || descText.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(AddDeals.this, "kindly fill all detail", Toast.LENGTH_SHORT).show();

                }
                else if(newDeal.getImage_url()==null){
                    Toast.makeText(AddDeals.this, "capture image of the deal first", Toast.LENGTH_SHORT).show();

                }
                else {
                    newDeal.setDescription(descText.getText().toString());
                    newDeal.setPrice(priceText.getText().toString());
                    submitDeals(newDeal);
                }
                Toast.makeText(AddDeals.this, "deal saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageBtn.setImageBitmap(photo);
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File file = new File(getCacheDir(), "abc");

                    FileOutputStream fo = new FileOutputStream(file);
                    fo.write(bytes.toByteArray());
                    fo.flush();
                    fo.close();


                uploadfile(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }




    }


    private void uploadfile(File file){
        final ProgressDialog progressDialog = new ProgressDialog(AddDeals.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        Prefrences prefrences= new Prefrences();
        HashMap<String,String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("authorization","bearer "+prefrences.getTokenPreference(AddDeals.this));
        Call<LoginResponse> response = Api.getClient().uploadFile(stringStringHashMap,body );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                    // ArrayList<DealsModel> dealsModel = signUpResponsesData.getData();
                    String imageUrl = (String) signUpResponsesData.getData().get("url");
                    newDeal.setImage_url(imageUrl);

                  //  Toast.makeText(AddDeals.this.getApplicationContext(), "Deal Added" +orderNumber, Toast.LENGTH_SHORT).show();

                }
                else {

                    try {
                        Toast.makeText(AddDeals.this, gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                Toast.makeText(AddDeals.this.getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }


    private void submitDeals(DealsModel deal){
        final ProgressDialog progressDialog = new ProgressDialog(AddDeals.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        HashMap<String, Object> jsonParams = new HashMap<>();
        //put something inside the map, could be null
        jsonParams.put("description", deal.getDescription());
        jsonParams.put("price", deal.getPrice());
        jsonParams.put("image_url", deal.getImage_url());

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


        Prefrences prefrences= new Prefrences();
        HashMap<String,String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("Content-Type","application/json;charset=UTF-8");
        stringStringHashMap.put("authorization","bearer "+prefrences.getTokenPreference(AddDeals.this));
        Call<LoginResponse> response = Api.getClient().saveDeal(stringStringHashMap,body );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){

                    String imageUrl = (String) signUpResponsesData.getData().get("url");
                    newDeal.setImage_url(imageUrl);

                  //  Toast.makeText(AddDeals.this.getApplicationContext(), "Deal Added" +orderNumber, Toast.LENGTH_SHORT).show();

                }
                else {

                    try {
                        Toast.makeText(AddDeals.this, gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                Toast.makeText(AddDeals.this.getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

    }










}
