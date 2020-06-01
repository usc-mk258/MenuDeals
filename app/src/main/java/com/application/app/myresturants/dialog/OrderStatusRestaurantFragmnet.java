package com.application.app.myresturants.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.R;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.GsonHelper;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.OrdersModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.HashMap;

import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusRestaurantFragmnet extends DialogFragment
{

    public OrdersModel model;
    private LoginResponse signUpResponsesData;
    GsonHelper gsonHelper;

    public static OrderStatusRestaurantFragmnet newInstance(int arg, OrdersModel complexVar) {
        OrderStatusRestaurantFragmnet frag = new OrderStatusRestaurantFragmnet();
        Bundle args = new Bundle();
        args.putInt("count", arg);
        args.putSerializable("order", complexVar);
        frag.setArguments(args);
        // frag.setComplexVariable(complexVar);
        return frag;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // index = getArguments().getInt(ARG_SECTION_NUMBER);
            model = (OrdersModel) getArguments().getSerializable("order");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_order_status_form_restaurant_fragment, container, false);
//getDialog().setTitle("Deal Info");
        // Do all the stuff to initialize your custom view
        Button submit,reject;
        gsonHelper = new GsonHelper();
      final   TextInputLayout textField2 = v.findViewById(R.id.textField2);;
        final  TextView textView6 =  v.findViewById(R.id.textView6);
        final  TextView textView7=  v.findViewById(R.id.textView7);
        final   TextInputEditText input =  v.findViewById(R.id.eta);

        submit = v.findViewById(R.id.submit);
        reject = v.findViewById(R.id.reject);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textField2.getVisibility()==View.GONE){
                    textField2.setVisibility(View.VISIBLE);
                    textView6.setVisibility(View.GONE);
                    textView7.setVisibility(View.GONE);
                }
                else {
                    if(input.getText().toString().equals("")){
                        Toast.makeText(getActivity(), "please enter ETA in hours", Toast.LENGTH_SHORT).show();
                    }else {
                        markDelivered(model.getId(),true,input.getText().toString());
                    }

                  //  markDelivered(model.getId());
                }

               //
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markDelivered(model.getId(),false,"0");
                Toast.makeText(getActivity(), "rejected call ", Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }




    private void markDelivered(String id,boolean isAccepted,String eta){
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
        stringStringHashMap.put("authorization","bearer "+prefrences.getTokenPreference(getContext()));
        Call<LoginResponse> response = Api.getClient().saveOrderStatusRes(stringStringHashMap,id ,isAccepted,eta);

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                    HashMap<String,Object> dealsModel = signUpResponsesData.getData();
                 //   ArrayList<ReviewModel> reviews = dealsModel.getReviews();
                    Toast.makeText(getActivity(), "Status Update!", Toast.LENGTH_SHORT).show();
getDialog().dismiss();


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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                Toast.makeText(getActivity().getApplicationContext(), t.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });


    }







}
