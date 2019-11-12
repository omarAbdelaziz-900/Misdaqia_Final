package com.example.misdaqia.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.misdaqia.Adapters.MainCategoriesAdapter;
import com.example.misdaqia.Helper.HeaderFontTextview;
import com.example.misdaqia.Helper.MainFontEdittext;
import com.example.misdaqia.Model.MainCategory;
import com.example.misdaqia.Model.MainCategoryResponse;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.ViewUtil.ViewUtil;
import com.tripl3dev.prettystates.StateExecuterKt;
import com.tripl3dev.prettystates.StatesConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private RecyclerView categoryRecycler;
    private RecyclerView mazadat_recycler;
    HeaderFontTextview txt_userName;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    MainFontEdittext txt_search;
//    SearchView txt_search;
    LinearLayoutManager linearLayoutManager;
    MainCategoriesAdapter mainCategoriesAdapter;
    List<MainCategoryResponse> mainCategoriesList;
    ArrayList<String> names;

    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);
        getCategories();
        searchFilte();
    }



    private void initView(View view) {
    if (getActivity()!=null)
        ViewUtil.hideKeyboard(getActivity());
        mainCategoriesList=new ArrayList<>();
        names=new ArrayList<>();
        categoryRecycler = (RecyclerView) view.findViewById(R.id.category_recycler);
        mazadat_recycler = (RecyclerView) view.findViewById(R.id.mazadat_recycler);
        txt_userName = view.findViewById(R.id.txt_userName);
        txt_search = view.findViewById(R.id.txt_search);
    }

    private void getCategories() {
        StateExecuterKt.setState(categoryRecycler, StatesConstants.LOADING_STATE);
        Call<List<MainCategoryResponse>> call = jsonPlaceHolderApi.getCategories();

        call.enqueue(new Callback<List<MainCategoryResponse>>() {
            @Override
            public void onResponse(Call<List<MainCategoryResponse>> call, Response<List<MainCategoryResponse>> response) {


                StateExecuterKt.setState(categoryRecycler, StatesConstants.NORMAL_STATE);
                mainCategoriesList=response.body();
                for (int i=0;i<mainCategoriesList.size();i++) {
                names.add(mainCategoriesList.get(i).getName());
                    Log.d(TAG, "namesnames: " + names+"");
                }
                initMainCategoriesRecyclerView();
//                initMzadCategoriesRecyclerView();
            }

            @Override
            public void onFailure(Call<List<MainCategoryResponse>> call, Throwable t) {
                StateExecuterKt.setState(categoryRecycler, StatesConstants.NORMAL_STATE);

                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMainCategoriesRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryRecycler.setItemAnimator(new DefaultItemAnimator());
        categoryRecycler.setNestedScrollingEnabled(false);
        categoryRecycler.setHasFixedSize(true);
        categoryRecycler.scrollToPosition(0);
        categoryRecycler.setNestedScrollingEnabled(false);
        mainCategoriesAdapter = new MainCategoriesAdapter(getActivity(), mainCategoriesList);
        categoryRecycler.setAdapter(mainCategoriesAdapter);
    }

    private void initMzadCategoriesRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mazadat_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mazadat_recycler.setItemAnimator(new DefaultItemAnimator());
        mazadat_recycler.setNestedScrollingEnabled(false);
        mazadat_recycler.setHasFixedSize(true);
        mazadat_recycler.scrollToPosition(0);
        mazadat_recycler.setNestedScrollingEnabled(false);
        mainCategoriesAdapter = new MainCategoriesAdapter(getActivity(), mainCategoriesList);
        mazadat_recycler.setAdapter(mainCategoriesAdapter);

    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<String> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (String s : names) {
            //if the existing elements contains the search input
            if (s.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        mainCategoriesAdapter.filterList(filterdNames);
    }



    public  void searchFilte(){
        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }
}
