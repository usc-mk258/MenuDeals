package com.application.app.myresturants.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.DealListAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.ReviewListAdapter;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.dialog.ConfirmDealFragment;
import com.application.app.myresturants.dialog.ReviewFormFragment;
import com.application.app.myresturants.helper.GsonHelper;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.DealResponse;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.RestaurantReviewModel;
import com.application.app.myresturants.models.RestautantModel;
import com.application.app.myresturants.models.ReviewModel;
import com.application.app.myresturants.models.ReviewResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReviewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    GsonHelper gsonHelper;
    private PageViewModel pageViewModel;
    RestautantModel restautantModel;
    RecyclerView recyclerView;
    private ReviewResponse signUpResponsesData;
    public static ReviewFragment newInstance(int index, RestautantModel restautantModel) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        bundle.putSerializable("restaurant", restautantModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_review, container, false);
       // final TextView textView = root.findViewById(R.id.section_label);
//        textView.setText("reviews");
        restautantModel = (RestautantModel) getArguments().getSerializable("restaurant");
        gsonHelper = new GsonHelper();
         recyclerView = root.findViewById(R.id.deals);
        getAllReviews(restautantModel.getId());
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ReviewFormFragment alertDialog = new ReviewFormFragment().newInstance(1,restautantModel);
                alertDialog.show(fm, "fragment_alert");
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });



        return root;
    }









    private void getAllReviews(String id){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();


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
        Call<ReviewResponse> response = Api.getClient().getReviewList(stringStringHashMap,id );

        response.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                RestaurantReviewModel dealsModel = signUpResponsesData.getData();
                    ArrayList<ReviewModel>  reviews = dealsModel.getReviews();



                    ReviewListAdapter adapter = new ReviewListAdapter(reviews,getContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);


                }
                else {

                    try {
                        Toast.makeText(getActivity(), gsonHelper.GsonJsonError(response.errorBody().string()).getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
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