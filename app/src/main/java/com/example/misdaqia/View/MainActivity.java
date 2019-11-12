package com.example.misdaqia.View;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.misdaqia.Adapters.MainCategoriesAdapter;
import com.example.misdaqia.Helper.HeaderFontTextview;
import com.example.misdaqia.Model.MainCategory;
import com.example.misdaqia.Model.MainCategoryResponse;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.View.login.SignInActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private RecyclerView categoryRecycler;
    private RecyclerView mazadatRecycler;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    View headerView;
    ProgressDialog progressDialog;
    HeaderFontTextview txt_userName;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);



//        getCategories();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

//    private void getCategories() {
//
//        progressDialog.show();
//            Call<MainCategoryResponse> call = jsonPlaceHolderApi.getCategories();
//
//
//            call.enqueue(new Callback<MainCategoryResponse>() {
//                @Override
//                public void onResponse(Call<MainCategoryResponse> call, Response<MainCategoryResponse> response) {
//
//                    List<MainCategory> list = response.body().getMainCategory();
//
//
//                    progressDialog.dismiss();
//
//                    categoryRecycler.setAdapter(new MainCategoriesAdapter( MainActivity.this,list));
//                }
//
//                @Override
//                public void onFailure(Call<MainCategoryResponse> call, Throwable t) {
//                    progressDialog.dismiss();
//
//                    Log.d(TAG, "onFailure: " + t.getMessage());
//                    Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//    }




    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
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

    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_main, menu);
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_sign, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(MainActivity.this, Profile.class));

        } else if (id == R.id.nav_mazadate) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_mazadfawre) {

        } else if (id == R.id.nav_mazadmaftoh) {

        } else if (id == R.id.nav_mazadmosar) {

        } else if (id == R.id.nav_elmahfza) {

        } else if (id == R.id.nav_logout) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(getString(R.string.exit))
                    .setMessage(getString(R.string.are_you_sure_to_exit))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(MainActivity.this, SignInActivity.class));
                            finish();
                        }

                    })
                    .setNegativeButton(getString(R.string.no), null)
                    .show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    private void initView() {
//        txt_userName = findViewById(R.id.txt_userName);
        toolbar = findViewById(R.id.toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        headerView = navigationView.getHeaderView(0);
        txt_userName = headerView.findViewById(R.id.txt_userName);

        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.please_wait));
        categoryRecycler = (RecyclerView) findViewById(R.id.category_recycler);
//        mazadatRecycler = (RecyclerView) findViewById(R.id.mazadat_recycler);

//        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

        categoryRecycler.setLayoutManager(new GridLayoutManager(this,2));
//        mazadatRecycler.setLayoutManager(new LinearLayoutManager(this));
        if (PreferenceHelper.getUserFirstName(this)!=null){
            txt_userName.setText(PreferenceHelper.getUserFirstName(MainActivity.this));
//            Toast.makeText(this, PreferenceHelper.getUserFirstName(MainActivity.this)+"", Toast.LENGTH_SHORT).show();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////


}
