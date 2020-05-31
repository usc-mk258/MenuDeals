package com.application.app.myresturants.api;



import com.application.app.myresturants.models.CustomerOrderResponse;
import com.application.app.myresturants.models.DealResponse;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestauranResponse;
import com.application.app.myresturants.models.RestaurantListModel;
import com.application.app.myresturants.models.ReviewResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

   // annotation used in POST type requests
    @POST("customer/signin")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> registration(@Body RequestBody map);


    @POST("customer/nearby-restaurant")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<RestauranResponse> getRestaurantList(@Body RequestBody map);


    @GET("customer/restaurant-deals/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<DealResponse> getDealList(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);


    @GET("customer/orders")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<CustomerOrderResponse> getCustomerOrder(@HeaderMap Map<String, String> headers);


    @GET("customer/restaurant-reviews/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<ReviewResponse> getReviewList(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);


    @GET("customer/order/received/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveOrderStatus(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);

    @POST("customer/restaurant-reviews/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveReview(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id,@Body RequestBody map);


    @POST("customer/place-order/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> subDeal(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);

    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class
}
