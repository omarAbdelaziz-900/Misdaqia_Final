package com.example.misdaqia.View.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.misdaqia.Helper.MainFontButton;
import com.example.misdaqia.Helper.MainFontEdittext;
import com.example.misdaqia.Model.LoginUserResponse;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.View.HomeActivity;
import com.example.misdaqia.View.MainActivity;
import com.example.misdaqia.View.register.SignUpActivity;
import com.example.misdaqia.ViewUtil.ViewUtil;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private MainFontEdittext edtemail;
    private MainFontEdittext edtpassword;
    private MainFontButton btnlogin;

    ProgressDialog progressDialog;

    JsonPlaceHolderApi jsonPlaceHolderApi;
    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        initView();

        initActios();

        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);
    }

    private void initActios() {

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtemail.getText().toString();
                String password = edtpassword.getText().toString();

                if (checkValidation() == false) {
                    return;
                } else {
//                    loginUser(email, password);
                    ViewUtil.hideKeyboard(SignInActivity.this);
                    loginUser(new LoginRequest(email,password));
                }
            }
        });
    }

    private void loginUser(LoginRequest loginRequest) {

        progressDialog.show();

//        Call<LoginResponse> call = jsonPlaceHolderApi.loginForUser(loginRequest,"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjBlZjAxYTk1M2YxNWQyMWQ3MDRiZmZkMTkyYjcwMzRjMmU2ZGU4OTkyMzI3NTY1NTA1ZDJmMDZmNTRhYzA4MDliMGY4Zjk1ZTYyZGExNjc0In0.eyJhdWQiOiIxIiwianRpIjoiMGVmMDFhOTUzZjE1ZDIxZDcwNGJmZmQxOTJiNzAzNGMyZTZkZTg5OTIzMjc1NjU1MDVkMmYwNmY1NGFjMDgwOWIwZjhmOTVlNjJkYTE2NzQiLCJpYXQiOjE1NzI0NDY5NDUsIm5iZiI6MTU3MjQ0Njk0NSwiZXhwIjoxNjA0MDY5MzQ1LCJzdWIiOiIzOCIsInNjb3BlcyI6WyJ1c2VyIl19.hwmA4JLvnYQC8WGAp0VvueRAh73o8juGzzedVIoegEaqphhX6waPe3x5iJOMEo0ayndDce1LwM1QJCcN5CtNWocLNp460M6YGGM3znK6Y64y8ciQQxm3D3Tr5GKbvJ-3Xhfx6_0N73BUki0fenbsUreS-76lIRJrcjiReTs_KT98BOei3IjtCofzn38hz31XXMVIP4hFNQci_iwg8-csaD2YlgFkYfiqacYHPcH5vUh6mk0n1cZnRnK9Zl10Uo7JWMF42d1zQ6GStWhMJP5AiSTAPqLsZ80hwUv9sScC6ZBhDrrslaCE6_tHrXZovlOZwuIJOR7noLmQprjk4pVXB-oRJPksCWNPMb8hZqei0YsL00QWevn_wwHuXzb5VHPITDRKw3t9FJoqTt1qksoEIJwzLQWfaB92reWNPVTC8KItX09wvqVNssRm4Sij9GhXYK9a1bKiY9Bou-4_lxAj1EX1hTl7rwl-uQs8F30h79XXUO9tg0w0-2OG4yhU1c095gaI8mG8bQ42xHrfRs_WJPZ0gHU-mgz-VelwxT-8YM_F1wIZMhdD5eZOhj8OHYiFjXmLJ6VWWZut3zJSM2ypGZ65uRS3e99NJ9PjWP60J3L4dMwlay5yYL_dAh9OZRMjKqxp8q2WVlaxVgUZ5hN3WW4Xupv3GT0-MtfqeDT-Wps");
        Call<LoginResponse> call = jsonPlaceHolderApi.loginForUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){

                    LoginResponse loginResponse=response.body();

                    if (loginResponse.isStatus()){
                        Toast.makeText(SignInActivity.this, getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                        PreferenceHelper.setTokenForUser(SignInActivity.this,loginResponse.getToken());
                        PreferenceHelper.setUserFirstName(SignInActivity.this,loginResponse.getUser().getName());
                        Log.e("userName",loginResponse.getUser().getName());
                        PreferenceHelper.setRemmemberMe(SignInActivity.this,true);
                        PreferenceHelper.setUsetId(SignInActivity.this,loginResponse.getUser().getId());
                        navigateToHome();
                    }else {
                        Toast.makeText(SignInActivity.this,getString(R.string.fail), Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }else {

                    Toast.makeText(SignInActivity.this,getString(R.string.fail) , Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    Toast.makeText(SignInActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }

                Log.e("errorFromLogin",t+"");
                progressDialog.dismiss();

            }
        });


    }


    public void goSignup(View view) {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }


    private void initView() {
        edtemail = (MainFontEdittext) findViewById(R.id.edtemail);
        edtpassword = (MainFontEdittext) findViewById(R.id.edtpassword);
        btnlogin = (MainFontButton) findViewById(R.id.btnlogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));
        progressDialog.setCancelable(false);
    }

    private boolean checkValidation() {

        boolean flag = false;

        if (edtemail.getText().toString().length() == 0) {
            edtemail.setError(getString(R.string.empty_email));
            edtemail.requestFocus();
            flag = false;

        } else if (edtpassword.getText().toString().length() == 0) {
            edtpassword.setError(getString(R.string.empty_password));
            edtpassword.requestFocus();
            flag = false;

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString().trim()).matches()) {
            edtemail.setError(getString(R.string.invalid_email));
            edtemail.requestFocus();
            flag = false;
        } else {
            flag = true;
        }

        return flag;

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.are_you_sure_to_exit))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }

                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }


    void navigateToHome(){
        Intent intent=new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
