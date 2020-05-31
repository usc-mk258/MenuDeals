package com.application.app.myresturants.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.application.app.myresturants.CustomerActivity;
import com.application.app.myresturants.DealAdapter;
import com.application.app.myresturants.LoginActivity;
import com.application.app.myresturants.R;
import com.application.app.myresturants.RestaurantAdapter;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Constants;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestauranResponse;
import com.application.app.myresturants.models.RestaurantListModel;
import com.application.app.myresturants.models.RestautantModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    private RecyclerView restaurants;
    private HomeViewModel homeViewModel;
    private RestaurantAdapter dataAdapter;
    private RestauranResponse signUpResponsesData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        restaurants = root.findViewById(R.id.restaurants_rv);







        getAllRestaurants();








        return root;
    }




    private void getAllRestaurants(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        HashMap<String, Object> jsonParams = new HashMap<>();
        //put something inside the map, could be null
        jsonParams.put("latitude", "24.8464");
        jsonParams.put("longitude", "67.0244");

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

/*
    RequestBody body =
            RequestBody.create(MediaType.parse("text/plain"),( new JSONObject(jsonParams)).toString());


    String bodyString = jsonBody + "?grant_type=" +
            grantType + "&scope=" + scope;
    TypedInput requestBody = new TypedByteArray(
            "application/json", bodyString.getBytes(Charset.forName("UTF-8")));*/

        //  RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Call<RestauranResponse> response = Api.getClient().getRestaurantList( body);

        response.enqueue(new Callback<RestauranResponse>() {
            @Override
            public void onResponse(Call<RestauranResponse> call, Response<RestauranResponse> response) {
                signUpResponsesData = response.body();
                if(signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                   // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                   ArrayList<RestautantModel> restautantModels = signUpResponsesData.getData().get("restaurants");
                    int numberOfColumns = 2;
                    restaurants.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
                    dataAdapter  = new RestaurantAdapter( restautantModels,getActivity());
                    restaurants.setAdapter(dataAdapter);


             //       Toast.makeText(getActivity().getApplicationContext(), "Cannot login please try again with correct credentials ", Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getActivity().getApplicationContext(), "Cannot login please try again with correct credentials ", Toast.LENGTH_SHORT).show();
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<RestauranResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                Toast.makeText(getActivity().getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
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

}
