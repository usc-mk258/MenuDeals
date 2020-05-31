package com.application.app.myresturants.ui.cutomerorder;

import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.application.app.myresturants.CustomerOrderAdapter;
import com.application.app.myresturants.OrderAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.ReviewListAdapter;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.CustomerOrderResponse;
import com.application.app.myresturants.models.OrderModel;
import com.application.app.myresturants.models.OrdersModel;
import com.application.app.myresturants.models.RestaurantReviewModel;
import com.application.app.myresturants.models.ReviewModel;
import com.application.app.myresturants.models.ReviewResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrderFragment extends Fragment {

    private CustomerOrderViewModel galleryViewModel;
    private CustomerOrderResponse signUpResponsesData;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_restaurant, container, false);


         recyclerView = root.findViewById(R.id.orders);
        getcustomerOrder();


        return root;
    }


    private void getcustomerOrder(){
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
        Call<CustomerOrderResponse> response = Api.getClient().getCustomerOrder(stringStringHashMap);

        response.enqueue(new Callback<CustomerOrderResponse>() {
            @Override
            public void onResponse(Call<CustomerOrderResponse> call, Response<CustomerOrderResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");

                    ArrayList<OrdersModel> orderList = signUpResponsesData.getData().get("orders");

                    CustomerOrderAdapter adapter = new CustomerOrderAdapter(orderList,getActivity());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);


                  /*  RestaurantReviewModel dealsModel = signUpResponsesData.getData();
                    ArrayList<ReviewModel>  reviews = dealsModel.getReviews();



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
            public void onFailure(Call<CustomerOrderResponse> call, Throwable t) {
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

    private ArrayList<OrderModel> getDummyData(){
        ArrayList<OrderModel> ordersList = new ArrayList<>();
        OrderModel orderModel1 =new OrderModel();

        orderModel1.setSrNumber("1");
        orderModel1.setOrderNumber("101");
        orderModel1.setProduct("Frozen yoghurt");
        orderModel1.setOrderedBy("John");
        orderModel1.setDate("03-04-2020");
        orderModel1.setStatus("New");

        ordersList.add(orderModel1);


        OrderModel orderModel2 =new OrderModel();

        orderModel2.setSrNumber("2");
        orderModel2.setOrderNumber("102");
        orderModel2.setProduct("Ice cream sandwich");
        orderModel2.setOrderedBy("Berlin");
        orderModel2.setDate("03-04-2020");
        orderModel2.setStatus("Pendig");

        ordersList.add(orderModel2);


        OrderModel orderModel3 =new OrderModel();

        orderModel3.setSrNumber("3");
        orderModel3.setOrderNumber("103");
        orderModel3.setProduct("Cupcake");
        orderModel3.setOrderedBy("Professor");
        orderModel3.setDate("03-04-2020");
        orderModel3.setStatus("Rejected");

        ordersList.add(orderModel3);




        return ordersList;

    }
}
