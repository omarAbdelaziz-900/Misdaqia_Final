package com.example.misdaqia.View.register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.misdaqia.Helper.MainFontButton;
import com.example.misdaqia.Helper.MainFontEdittext;
import com.example.misdaqia.Model.User;
import com.example.misdaqia.Model.registerUserResponse;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.View.MainActivity;
import com.example.misdaqia.View.login.SignInActivity;
import com.example.misdaqia.localizationUtil.Localization;

import java.net.ConnectException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private CircleImageView profileProfileImage;
    private MainFontEdittext edtname;
    private MainFontEdittext edtemail;
    private MainFontEdittext edtpassword;
    private MainFontEdittext edtPasswordVerified;
    private MainFontButton signBtn;

    ProgressDialog progressDialog;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    RelativeLayout relative_back;

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        initActions();

        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);

        relative_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private void initActions() {
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtname.getText().toString();
                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();
                String re_password = edtPasswordVerified.getText().toString();

                if (!checkValidation()) {
                    return;
                } else {
//                    createUser2(new RegisterRequest(name, email, password, re_password));
                    createUser(name, email, password, re_password);

                }

            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////



    private void createUser(String name, String email, String password, String re_password) {

        progressDialog.show();

        Call<RegisterResponse> call = jsonPlaceHolderApi.registerForUser(new RegisterRequest(name, email, password, re_password,"client"));

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }


                if (response.code() == 500) {
                    Toast.makeText(SignUpActivity.this, getString(R.string.exit), Toast.LENGTH_SHORT).show();
                }

                RegisterResponse registerResponse = response.body();

                if (registerResponse.isStatus()){
                    progressDialog.dismiss();
//                    if (Localization.getCurrentLanguageID(SignUpActivity.this)==Localization.arabic){
                        Toast.makeText(SignUpActivity.this, getString(R.string.account_created), Toast.LENGTH_SHORT).show();
//
//
                }else {
                    Toast.makeText(SignUpActivity.this, getString(R.string.fail), Toast.LENGTH_SHORT).show();
                }
                navigateToLogin();
            }


            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: " + t.getMessage());
//                Toast.makeText(SignUpActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUpActivity.this, getString(R.string.change_email), Toast.LENGTH_SHORT).show();

                if (t instanceof ConnectException) {
                    Toast.makeText(SignUpActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



//    private void createUser(String name, String email, String password, String re_password) {
//
//        progressDialog.show();
//
//        Call<RegisterResponse> call = jsonPlaceHolderApi.createUser(name, email, password, re_password);
//
//        call.enqueue(new Callback<RegisterResponse>() {
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//
//                if (!response.isSuccessful()) {
//                    Toast.makeText(SignUpActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//
//                }
//
//
//                if (response.code() == 500) {
//                    Toast.makeText(SignUpActivity.this, getString(R.string.exit), Toast.LENGTH_SHORT).show();
//                }
//
//                RegisterResponse registerResponse = response.body();
//
//                if (registerResponse.isStatus()){
//                    progressDialog.dismiss();
////                    if (Localization.getCurrentLanguageID(SignUpActivity.this)==Localization.arabic){
//                        Toast.makeText(SignUpActivity.this, getString(R.string.account_created), Toast.LENGTH_SHORT).show();
////
////
//                }else {
//                    Toast.makeText(SignUpActivity.this, getString(R.string.fail), Toast.LENGTH_SHORT).show();
//                }
//                navigateToLogin();
//            }
//
//
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                progressDialog.dismiss();
//                Log.d(TAG, "onFailure: " + t.getMessage());
////                Toast.makeText(SignUpActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                if (t instanceof ConnectException) {
//                    Toast.makeText(SignUpActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void backLogin() {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private void initView() {
        relative_back=findViewById(R.id.relative_back);
        profileProfileImage = (CircleImageView) findViewById(R.id.profile_profile_image);
        edtname = (MainFontEdittext) findViewById(R.id.edtname);
        edtemail = (MainFontEdittext) findViewById(R.id.edtemail);
        edtpassword = (MainFontEdittext) findViewById(R.id.edtpassword);
        edtPasswordVerified = (MainFontEdittext) findViewById(R.id.edtverifiedpassword);
        signBtn = (MainFontButton) findViewById(R.id.signBtn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean checkValidation() {

        boolean flag = false;

        if (edtname.getText().toString().length() == 0) {
            edtname.setError(getString(R.string.empty_name));
            edtname.requestFocus();
            flag = false;

        } else if (edtemail.getText().toString().length() == 0) {
            edtemail.setError(getString(R.string.empty_email));
            edtemail.requestFocus();
            flag = false;

        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
            edtemail.setError(getString(R.string.invalid_email));
            edtemail.requestFocus();
            flag = false;
        }  else if (edtpassword.getText().toString().length() == 0) {
            edtpassword.setError(getString(R.string.empty_password));
            edtpassword.requestFocus();
            flag = false;

        } else if (edtPasswordVerified.getText().toString().length() == 0) {
            edtPasswordVerified.setError(getString(R.string.empty_password));
            edtPasswordVerified.requestFocus();
            flag = false;

        } else if (!edtPasswordVerified.getText().toString().equals(edtpassword.getText().toString())) {
            edtPasswordVerified.setError(getString(R.string.not_equal_password));
            edtPasswordVerified.requestFocus();
            flag = false;

        } else {
            flag = true;
        }

        return flag;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }


    void navigateToLogin(){
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        finish();
    }
}

