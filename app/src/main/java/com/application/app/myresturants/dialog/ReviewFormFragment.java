package com.application.app.myresturants.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.R;
import com.application.app.myresturants.ReviewListAdapter;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestaurantReviewModel;
import com.application.app.myresturants.models.RestautantModel;
import com.application.app.myresturants.models.ReviewModel;
import com.application.app.myresturants.models.ReviewResponse;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFormFragment extends DialogFragment
{

    public RestautantModel model;
    private LoginResponse signUpResponsesData;


    public static ReviewFormFragment newInstance(int arg, RestautantModel complexVar) {
        ReviewFormFragment frag = new ReviewFormFragment();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        args.putSerializable("deal", complexVar);
        frag.setArguments(args);
        // frag.setComplexVariable(complexVar);
        return frag;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // index = getArguments().getInt(ARG_SECTION_NUMBER);
            model = (RestautantModel) getArguments().getSerializable("deal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_review_form_fragment, container, false);
//getDialog().setTitle("Deal Info");
        // Do all the stuff to initialize your custom view
        Button submit;
        final RatingBar ratingBar;
        final TextInputEditText desc;
        ratingBar = v.findViewById(R.id.ratingBar);
        desc = v.findViewById(R.id.desc_input);
        submit = v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(desc.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill description", Toast.LENGTH_SHORT).show();
                }else if(ratingBar.getRating()==0){
                    Toast.makeText(getActivity(), "Please rate before submit", Toast.LENGTH_SHORT).show();
                }
                else {

                    saveReview(model.getId(),desc.getText().toString(),ratingBar.getRating());
                }
               // Toast.makeText(getActivity(), model.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }





    private void saveReview(String id,String desc,float rating){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();
        HashMap<String, Object> jsonParams = new HashMap<>();
        //put something inside the map, could be null
        jsonParams.put("description", desc);
        jsonParams.put("rating", rating);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());


        //  RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

/*
    RequestBody body =
            RequestBody.create(MediaType.parse("text/plain"),( new JSONObject(jsonParams)).toString());


    String bodyString = jsonBody + "?grant_type=" +
            grantType + "&scope=" + scope;
    TypedInput requestBody = new TypedByteArray(
            "application/json", bodyString.getBytes(Charset.forName("UTF-8")));*/

        //  RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Prefrences prefrences= new Prefrences();
        HashMap<String,String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("Content-Type","application/json;charset=UTF-8");
        stringStringHashMap.put("authorization","bearer "+prefrences.getStringPreference(getContext()));
        Call<LoginResponse> response = Api.getClient().saveReview(stringStringHashMap,id,body );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    Toast.makeText(getActivity(), "Thank you for your review", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                /*    RestaurantReviewModel dealsModel = signUpResponsesData.getData();
                    ArrayList<ReviewModel> reviews = dealsModel.getReviews();



                    ReviewListAdapter adapter = new ReviewListAdapter(reviews,getContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);*/


                }
                else {

                    Toast.makeText(getActivity().getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
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
