package com.application.app.myresturants.dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.app.myresturants.DealListAdapter;
import com.application.app.myresturants.R;
import com.application.app.myresturants.api.Api;
import com.application.app.myresturants.helper.Prefrences;
import com.application.app.myresturants.models.DealResponse;
import com.application.app.myresturants.models.DealsModel;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestautantModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmDealFragment extends DialogFragment
{
    public DealsModel model;
    private LoginResponse signUpResponsesData;

    public static ConfirmDealFragment newInstance(int arg, DealsModel complexVar) {
        ConfirmDealFragment frag = new ConfirmDealFragment();
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
            model = (DealsModel) getArguments().getSerializable("deal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_confirm_order_fragment, container, false);
//getDialog().setTitle("Deal Info");
        // Do all the stuff to initialize your custom view

        Button submit = v.findViewById(R.id.submit);
        ImageView imageView = v.findViewById(R.id.imageView2);
        TextView desc = v.findViewById(R.id.desc);
        TextView price = v.findViewById(R.id.textView5);
desc.setText(model.getDescription());
price.setText(model.getPrice());
        Glide.with(getActivity()).load(model.getImage_url()).centerCrop().into(imageView);



submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        submitDeals(model.getId());
        //   Toast.makeText(getActivity(), "han bahi kardo submit :"+model.getDescription(), Toast.LENGTH_SHORT).show();
    }
});


        return v;
    }

    private void submitDeals(String id){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();


        Prefrences prefrences= new Prefrences();
        HashMap<String,String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("Content-Type","application/json;charset=UTF-8");
        stringStringHashMap.put("authorization","bearer "+prefrences.getStringPreference(getContext()));
        Call<LoginResponse> response = Api.getClient().subDeal(stringStringHashMap,id );

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signUpResponsesData = response.body();
                if(response.code()==200 && signUpResponsesData.isSuccess()){
                    //Prefrences prefrences = new Prefrences();
                    // prefrences.putStringPreference(getActivity(), Constants.FILENAME,Constants.AUTHENTICATE_USER_TOKEN,signUpResponsesData.getData().get(0)+"");
                   // ArrayList<DealsModel> dealsModel = signUpResponsesData.getData();
                   String orderNumber = (String) signUpResponsesData.getData().get("orderId");


                       Toast.makeText(getActivity().getApplicationContext(), "Order has been placed, your order number is:" +orderNumber, Toast.LENGTH_SHORT).show();
                       getDialog().dismiss();
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
