package com.example.misdaqia.Services;

import com.example.misdaqia.Model.JularyObjectModel;
import com.example.misdaqia.Model.LoginUserResponse;
import com.example.misdaqia.Model.MainCategory;
import com.example.misdaqia.Model.MainCategoryResponse;
import com.example.misdaqia.Model.registerUserResponse;
import com.example.misdaqia.View.PaymentPage.PaymentRequest;
import com.example.misdaqia.View.PaymentPage.PaymentResponse;
import com.example.misdaqia.View.login.LoginRequest;
import com.example.misdaqia.View.login.LoginResponse;
import com.example.misdaqia.View.register.RegisterRequest;
import com.example.misdaqia.View.register.RegisterResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {


    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> createUser(
            @Field("name") String Name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String re_password);

//    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> registerForUser(@Body RegisterRequest registerRequest);


    @FormUrlEncoded
    @POST("login")
    Call<LoginUserResponse> Login(@FieldMap Map<String,String> fields);

    @POST("login")
    Call<LoginResponse> loginForUser(@Body LoginRequest loginRequest);
//    Call<LoginResponse> loginForUser(@Body LoginRequest loginRequest,@Header("Authorization") String auth);

//    @GET("getCategoryApi")
//    Call<MainCategoryResponse> getCategories();

    @GET("getCategories")
    Call<List<MainCategoryResponse>> getCategories();

    @GET("getMazadApi")
    Call<MainCategoryResponse> getMazadat();

    @GET("getJewelryApi ")
    Call<JularyObjectModel> getJulary();

    @GET("getVichleApi ")
    Call<JularyObjectModel> getVichle();

    @GET("getcarApi ")
    Call<JularyObjectModel> getCars();

    @GET("getpropApi ")
    Call<JularyObjectModel> getBuildings();

    @GET("getHighValueApi ")
    Call<JularyObjectModel> getOther();

    @POST("payment")
    Call<PaymentResponse> performPayment(@Body PaymentRequest paymentRequest);

}