package com.application.app.myresturants.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.application.app.myresturants.DealListAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.RestaurantAdapter;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealModel;
import com.application.app.myresturants.models.DealResponse;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.RestauranResponse;
import com.application.app.myresturants.models.RestautantModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
RestautantModel restautantModel;
    private DealResponse signUpResponsesData;
    RecyclerView recyclerView;
    public static PlaceholderFragment newInstance(int index, RestautantModel restautantModel) {
        PlaceholderFragment fragment = new PlaceholderFragment();
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
            restautantModel = (RestautantModel) getArguments().getSerializable("restaurant");
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_deal_list, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        textView.setText("deals");
         recyclerView = root.findViewById(R.id.deals);

        getAllDeals(restautantModel.getId());



        /*pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }



    private void getAllDeals(String id){
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
        Call<DealResponse> response = Api.getClient().getDealList(stringStringHashMap,id );

        response.enqueue(new Callback<DealResponse>() {
            @Override
            public void onResponse(Call<DealResponse> call, Response<DealResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                    ArrayList<DealsModel> dealsModel = signUpResponsesData.getData();

                    DealListAdapter adapter = new DealListAdapter(dealsModel,getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                   /* int numberOfColumns = 2;
                    restaurants.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
                    dataAdapter  = new RestaurantAdapter( restautantModels,getActivity());
                    restaurants.setAdapter(dataAdapter);*/


                //    Toast.makeText(getActivity().getApplicationContext(), "Cannot login please try again with correct credentials ", Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(getActivity().getApplicationContext(), "Cannot login please try again with correct credentials ", Toast.LENGTH_SHORT).show();
                }


                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<DealResponse> call, Throwable t) {
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



    private ArrayList<DealModel> getDummyData(){
        ArrayList<DealModel> ordersList = new ArrayList<>();
        DealModel orderModel1 =new DealModel();

        orderModel1.setDealName("1");

        ordersList.add(orderModel1);


        DealModel orderModel2 =new DealModel();

        orderModel2.setDealName("2");
        ordersList.add(orderModel2);


        DealModel orderModel3 =new DealModel();

        orderModel3.setDealName("3");
        ordersList.add(orderModel3);




        return ordersList;

    }

}