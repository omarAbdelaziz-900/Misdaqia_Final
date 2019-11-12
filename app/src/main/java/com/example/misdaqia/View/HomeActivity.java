package com.example.misdaqia.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misdaqia.Adapters.NavigationDrawerAdapter;
import com.example.misdaqia.Model.NavigationDrawerItem;
import com.example.misdaqia.R;
import com.example.misdaqia.Utils.FragmentUtils;
import com.example.misdaqia.Utils.RecyclerDividerItemDecoration;
import com.example.misdaqia.View.login.SignInActivity;
import com.example.misdaqia.ViewUtil.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationDrawerAdapter.itemClicked{

    RecyclerView mDrawerRecyclerView;
    DrawerLayout mNavigationViewDrawerLayout;
    ImageView toggle_menu_iv;
    TextView toolbar_title;
    private NavigationDrawerAdapter mDrawerAdapter;
    private int notificationCountNumber = 0;
    //    NavigationDrawerItem[] drawerItem;
    List<NavigationDrawerItem> drawerItem;
    private int selectedItem = 1;

    private static final long MOVE_DEFAULT_TIME = 1000;
    private static final long FADE_DEFAULT_TIME = 300;

    private FragmentManager mFragmentManager;

    private Handler mDelayedTransactionHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        mDrawerRecyclerView=findViewById(R.id.drawer_recycler_view);
        mNavigationViewDrawerLayout=findViewById(R.id.drawer_layout);
        toggle_menu_iv=findViewById(R.id.toggle_menu_iv);
        toolbar_title=findViewById(R.id.toolbar_title);
        ViewUtil.hideKeyboard(HomeActivity.this);
        drawerItem = new ArrayList<>();

        mFragmentManager = getSupportFragmentManager();

        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_home_black_24dp, "الصفحه الرئيسيه"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_account_circle_black_24dp, "حسابي"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_shopping_cart_black_24dp, "الحقيبه"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_favorite_black_24dp, "مزاداتي"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_add_circle_outline_black_24dp, "اضافه مزاد فوري"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_add_circle_outline_black_24dp, "اضافه مزاد مفتوح"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_add_circle_outline_black_24dp, "اضافه مزاد مسعر"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_style_black_24dp, "المحفظه"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_settings_black_24dp, "الاعدادات"));
        drawerItem.add(new NavigationDrawerItem(R.drawable.ic_arrow_back_black_24dp, "تسجيل الخروج"));


        setupNavigationDrawer();
        openMenuDrawe();
        showFragment(new HomeFragment(), getString(R.string.app_name));
    }

    private void setupNavigationDrawer() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mDrawerRecyclerView.getLayoutParams();
        params.width = metrics.widthPixels;
        mDrawerRecyclerView.setLayoutParams(params);
        mDrawerRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mDrawerRecyclerView.setLayoutManager(mLayoutManager);
        mDrawerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDrawerRecyclerView.addItemDecoration(new RecyclerDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 15, true));
        mDrawerAdapter = new NavigationDrawerAdapter(getApplicationContext(), getData());
        mDrawerRecyclerView.setAdapter(mDrawerAdapter);
        mDrawerAdapter.setOnitemClicked(this);
    }

    private List<NavigationDrawerItem> getData() {
        List<NavigationDrawerItem> list = new ArrayList<>();
        list.add(new NavigationDrawerItem(NavigationDrawerItem.HEADER_VIEW_TYPE));
        list.add(new NavigationDrawerItem("الصفحه الرئيسيه", R.drawable.ic_home_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("حسابي", R.drawable.ic_account_circle_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("الحقيبه", R.drawable.ic_shopping_cart_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("مزاداتي", R.drawable.ic_favorite_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("اضافه مزاد فوري", R.drawable.ic_add_circle_outline_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("اضافه مزاد مفتوح", R.drawable.ic_add_circle_outline_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("اضافه مزاد مسعر", R.drawable.ic_add_circle_outline_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("المحفظه", R.drawable.ic_style_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("الاعدادات", R.drawable.ic_settings_black_24dp, NavigationDrawerItem.MENU_TYPE));
        list.add(new NavigationDrawerItem("تسجيل الخروج", R.drawable.ic_arrow_back_black_24dp, NavigationDrawerItem.MENU_TYPE));

        return list;
    }


    public void openMenuDrawe() {
        toggle_menu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });

    }

    @Override
    public void onItemClicked(View itemView, int position) {
        if (position == 1) {

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.am_frame_container);
            if(currentFragment instanceof HomeFragment){
//                CustomToast.with(this, "لقد تم فتح الصفحه بالفعل").show();
                closeDrawer();
            }else {
                showFragment(new HomeFragment(), getResources().getString(R.string.app_name));

            }
        }else if (position == 2) {
//            showFragment(new AboutUsFragment(),getResources().getString(R.string.about_us));
//            Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
        }else if (position == 3) {
//            showFragment(new ContactUsFragment(),getResources().getString(R.string.contact_us));
//            Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
        }else if (position == 4) {
//            showFragment(new SettingFragment(),getResources().getString(R.string.setting));
//            Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
        }else if (position == 9) {
            showFragment(new SettingFragment(),"الاعدادات");
//            Toast.makeText(this, position+"", Toast.LENGTH_SHORT).show();
        }else if (position == 10) {
            Intent intent=new Intent(HomeActivity.this, SignInActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void onCloseImageClicked(ImageView imageView ,int position) {
        closeDrawer();
        Log.e("kljlkj","l;klkl;klk;l");
    }

    public void setSelectedItem(int selectedItem) {
        int oldSelected = this.selectedItem;
        this.selectedItem = selectedItem;
        mDrawerAdapter.notifyDataSetChanged();
    }
    private void showFragment(Fragment fragment, String title) {
        toolbar_title.setText(title);
        FragmentUtils.replaceFragment(this, R.id.am_frame_container, fragment, null);
        closeDrawer();
    }

    public void closeDrawer(){
        if (mNavigationViewDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mNavigationViewDrawerLayout.closeDrawer(GravityCompat.START);
            Log.e("inni","l;inni;inni;l");
        }
    }

    public void openDrawer(){
        if (mNavigationViewDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mNavigationViewDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mNavigationViewDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
