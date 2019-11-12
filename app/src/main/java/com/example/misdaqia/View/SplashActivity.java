package com.example.misdaqia.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.misdaqia.Common.Common;
import com.example.misdaqia.Model.LoginUserResponse;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.View.PaymentPage.WebviewActivity;
import com.example.misdaqia.View.login.SignInActivity;
import com.example.misdaqia.localizationUtil.Localization;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    int timeSec;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Localization.SetLanguage(SplashActivity.this, Localization.arabic);

        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);


        Thread splash_screen = new Thread() {
            //for not moving to any activity after 3000
            @Override
            public void run() {
                // This method will be executed once the timer is over
                timeSec = 1500;
                // Start your app main activity
                try {
                    sleep(timeSec);

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {

                    // it must return to main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences share = getSharedPreferences("data", Context.MODE_PRIVATE);
                            String email = share.getString("email", null);
                            String password = share.getString("password", null);

                            if (!TextUtils.isEmpty(email)) {
                                Login(email, password);

                            }else {

                                if (PreferenceHelper.getRemmemberMe(SplashActivity.this)) {
//                                     intent = new Intent(SplashActivity.this, WebviewActivity.class);
                                     intent = new Intent(SplashActivity.this, HomeActivity.class);
                                }else {
                                     intent = new Intent(SplashActivity.this, SignInActivity.class);
                                }
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        };
        splash_screen.start();
    }


    private void Login(final String Email, final String Password) {


        if (Common.isConnectToInternet(getBaseContext())) {

//            final ProgressDialog progressDialog = new ProgressDialog(SplashActivity.this);
//            progressDialog.setMessage("please wait ....");
//            progressDialog.show();

            Map<String, String> parameters = new HashMap<>();
            parameters.put("password",Password);
            parameters.put("email",Email);

            Call<LoginUserResponse> call = jsonPlaceHolderApi.Login(parameters);

            call.enqueue(new Callback<LoginUserResponse>() {
                @Override
                public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {


                    LoginUserResponse loginUserResponse = response.body();

                    if (loginUserResponse.getEmail().equals(Email)) {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                        intent = new Intent(SplashActivity.this, WebviewActivity.class);

                        finish();

                    } else {
                        startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                        finish();
                    }


                }


                @Override
                public void onFailure(Call<LoginUserResponse> call, Throwable t) {
//                    progressDialog.dismiss();
//                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(SplashActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


        } else {
            Toast.makeText(SplashActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }

    }



}
