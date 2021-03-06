package com.application.app.myresturants.api;



import com.application.app.myresturants.models.CustomerOrderResponse;
import com.application.app.myresturants.models.DealResponse;
import com.application.app.myresturants.models.LoginResponse;
import com.application.app.myresturants.models.RestauranResponse;
import com.application.app.myresturants.models.RestaurantListModel;
import com.application.app.myresturants.models.RestaurantOrderResponse;
import com.application.app.myresturants.models.ReviewResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

   // annotation used in POST type requests
    @POST("customer/signin")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> signin(@Body RequestBody map);

    @POST("restaurant/signin")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> loginRestaurant(@Body RequestBody map);


    @POST("customer/nearby-restaurant")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<RestauranResponse> getRestaurantList(@Body RequestBody map);



    @POST("customer/location")
   // @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> updateLocation(@HeaderMap Map<String, String> headers,@Body RequestBody map);


    @POST("customer/signup")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> signUpCustomer(@Body RequestBody map);


    @POST("restaurant/signup")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> signUpRestaurant(@Body RequestBody map);


    @GET("customer/restaurant-deals/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<DealResponse> getDealList(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);



    @GET("restaurant/deals/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<DealResponse> getRestaurantDealList(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);


    @GET("customer/orders")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<CustomerOrderResponse> getCustomerOrder(@HeaderMap Map<String, String> headers);

    @GET("restaurant/orders")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<RestaurantOrderResponse> getRestaurantOrder(@HeaderMap Map<String, String> headers);


    @GET("customer/restaurant-reviews/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<ReviewResponse> getReviewList(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);



    @GET("restaurant/reviews")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<ReviewResponse> getRestaurantReviewList(@HeaderMap Map<String, String> headers);


    @GET("customer/order/received/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveOrderStatus(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);




    @GET("restaurant/info")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> getProfile(@HeaderMap Map<String, String> headers);



    @GET("restaurant/order/mark/{id}/{status}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveOrderStatusRes(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id,@Path(value = "status", encoded = true) boolean status,@Query("eta") String eta);

    @POST("customer/restaurant-review /{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveReview(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id,@Body RequestBody map);



    @POST("restaurant/deal")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveDeal(@HeaderMap Map<String, String> headers,@Body RequestBody map);


    @POST("restaurant/add-info")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> saveInfo(@HeaderMap Map<String, String> headers,@Body RequestBody map);


    @POST("customer/place-order/{id}")
  //  @Headers({ "Content-Type: application/json;charset=UTF-8"})
        // API's endpoints
    Call<LoginResponse> subDeal(@HeaderMap Map<String, String> headers, @Path(value = "id", encoded = true) String id);


 @Multipart
 @POST("file-upload")
 Call<LoginResponse> uploadFile(@HeaderMap Map<String, String> headers,@Part MultipartBody.Part file);


 @Multipart
 @POST("file-upload")
 Call<LoginResponse> uploadPhoto(@Header("Access-Token") String header, @Part MultipartBody.Part imageFile);
}
