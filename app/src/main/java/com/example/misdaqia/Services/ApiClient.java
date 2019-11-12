package com.example.misdaqia.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    public static final String BASE_URL = "https://mesdaqia2.000webhostapp.com/api/";
    public static final String BASE_URL = "http://test.misdaqia.com/api/";
//    public static Retrofit retrofit = null;

//    public static Retrofit getApiClient(){
//
//        if(retrofit == null){
//
//            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .build();
//
//
//
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }


    private static Retrofit retrofit = null;

     public static Retrofit getApiClient() {

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//
//
//        return retrofit;


//         Gson gson = new GsonBuilder()
//                 .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
//                 .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//                 .create();

         Gson gson = new GsonBuilder()
                 .setLenient()
                 .create();

         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

         OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                 .readTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).build();


          retrofit = new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .client(client)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
         return retrofit;

    }

}

