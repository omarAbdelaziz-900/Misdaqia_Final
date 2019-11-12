package com.example.misdaqia.View.PaymentPage;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.misdaqia.Adapters.CustomAdapterSpinner;
import com.example.misdaqia.Helper.MainFontButton;
import com.example.misdaqia.Helper.MainFontEdittext;
import com.example.misdaqia.Helper.MainFontTextview;
import com.example.misdaqia.R;
import com.example.misdaqia.Services.ApiClient;
import com.example.misdaqia.Services.JsonPlaceHolderApi;
import com.example.misdaqia.SharedPreferences.PreferenceHelper;
import com.example.misdaqia.Utils.Constants;
import com.example.misdaqia.Utils.ValidationTool;
import com.example.misdaqia.View.HomeActivity;
import com.example.misdaqia.ViewUtil.ViewUtil;
import com.tripl3dev.prettystates.StateExecuterKt;
import com.tripl3dev.prettystates.StatesConstants;

import java.net.ConnectException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class WebviewActivity extends AppCompatActivity {

//    WebView webView;
    MainFontTextview txt_back;
    ImageView arrow_back;
    RadioGroup radioGroupPayment;
    RadioButton cashRadioButton,onlineRadioButton;
    MainFontEdittext amount_et,card_number_et,card_cvv_et;
    MainFontTextview card_expire_date;
    Spinner card_expire_date_et;
    Spinner card_expire_year_et;
    MainFontButton payment_btn;
    RelativeLayout payment_container;
    int paymentType=0;
    String paymentTypestr;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    ValidationTool validationTool;
    ArrayList<String>years,months;
    int year=0,month=0;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();
    String cardExpireDate;
    CheckBox visa_chk,master_chk;
    ScrollView scrollView;
    View view_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initView();
        backAction();
        radioButtonsHandleActions();
        handelCheckBoxClick();
        initYear();
        initMonth();
        actionDate();
        clickPayment();
        hideKeyboard();
//        ViewUtil.initScrollView(scrollView);
    }
    void initView(){
//        webView = findViewById(R.id.webview);
        ViewUtil.hideKeyboard(this);

        jsonPlaceHolderApi = ApiClient.getApiClient().create(JsonPlaceHolderApi.class);
        validationTool=new ValidationTool(this);
        scrollView = findViewById(R.id.scrollView);
        payment_container = findViewById(R.id.payment_container);
        payment_btn = findViewById(R.id.payment_btn);
        amount_et = findViewById(R.id.amount_et);

        visa_chk = findViewById(R.id.visa_chk);
        master_chk = findViewById(R.id.master_chk);
//        amount_et.setFocusable(false);
        card_number_et = findViewById(R.id.card_number_et);
        card_expire_date = findViewById(R.id.card_expire_date);
        card_expire_date_et = findViewById(R.id.card_expire_date_et);
        card_expire_year_et = findViewById(R.id.card_expire_year_et);
        card_cvv_et = findViewById(R.id.card_cvv_et);

        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        cashRadioButton = findViewById(R.id.cashRadioButton);
        onlineRadioButton = findViewById(R.id.onlineRadioButton);
        cashRadioButton.setChecked(false);
        onlineRadioButton.setChecked(false);
        txt_back = findViewById(R.id.txt_back);
        arrow_back = findViewById(R.id.arrow_back);
        view_loading = findViewById(R.id.view_loading);
        ViewUtil.hideKeyboard(this);
    }
    void backAction(){
        txt_back.setOnClickListener(v -> onBackPressed());
        arrow_back.setOnClickListener(v -> onBackPressed());
    }

    void webViewAction(){
        int id = getIntent().getIntExtra("id", 0);
//        int lang = new UserData().getLocalization(WebviewActivity.this);
//        String url = Constants.BASE_URL + "/Payment/PaymentOrder?OrderId=" + String.valueOf(id) + "&lang=" + String.valueOf(lang);
//        Log.e("Url" , url);
//        showLoading();
//        webView.loadUrl(url);

//        webView.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url) {
//                // do your stuff here
////                hideLoading();
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(this, HomeActivity.class);
//        intent.putExtra("From", " shipping order");
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }

    void radioButtonsHandleActions() {
        radioGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroupPayment.findViewById(checkedId);
                int index = radioGroupPayment.indexOfChild(radioButton);
                switch (index) {
                    case 0: // first button
                        // moassa
                        paymentType=1;
                        paymentTypestr= Constants.visa;
                        break;
                    case 1: // secondbutton
                        // myself
                        paymentType=2;
                        paymentTypestr= Constants.master;
                        break;
                }
            }
        });
    }

    private void performPayment(PaymentRequest paymentRequest) {

        StateExecuterKt.setState(view_loading, StatesConstants.LOADING_STATE);

        Call<PaymentResponse> call = jsonPlaceHolderApi.performPayment(paymentRequest);
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {

                if (response.isSuccessful()){

                    PaymentResponse paymentResponse=response.body();

                    if (paymentResponse.getResult()!=null)
                    if (paymentResponse.getResult().getCode().equals("000.200.000")){
                        Toast.makeText(WebviewActivity.this, getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                        Log.e("userName",paymentResponse.getResult().getCode());
                    }else {
//                        Toast.makeText(WebviewActivity.this, paymentResponse.getResult().getDescription(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(WebviewActivity.this,getString(R.string.fail), Toast.LENGTH_SHORT).show();
                    }

                    if (paymentResponse.getResult().getDescription().equals("invalid creditcard, bank account number or bank name")){
                        Toast.makeText(WebviewActivity.this, getString(R.string.card_number_invalid), Toast.LENGTH_SHORT).show();
                        Log.e("userName",paymentResponse.getResult().getDescription());
                    }else {
                        if (paymentResponse.getResult().getDescription().equals("invalid cc number/brand combination")){
                            Toast.makeText(WebviewActivity.this, getString(R.string.pay_type), Toast.LENGTH_SHORT).show();
                            Log.e("userName",paymentResponse.getResult().getDescription());
                        }
                    }

                    StateExecuterKt.setState(view_loading, StatesConstants.NORMAL_STATE);
                }else {

                    Toast.makeText(WebviewActivity.this,getString(R.string.fail) , Toast.LENGTH_SHORT).show();
                    StateExecuterKt.setState(view_loading, StatesConstants.NORMAL_STATE);
                }
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    Toast.makeText(WebviewActivity.this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }

                Log.e("errorFromLogin",t+"");
                StateExecuterKt.setState(view_loading, StatesConstants.NORMAL_STATE);

            }
        });

    }



//    void initYear() {
//        years = new ArrayList<String>();
//        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
//        years.add(getString(R.string.choose));
//        for (int i = 1990; i <= thisYear; i++) {
//            years.add(Integer.toString(i));
//        }
//        CustomAdapterSpinner customAdapter = new CustomAdapterSpinner(WebviewActivity.this, years);
//        card_expire_year_et.setAdapter(customAdapter);
//        ViewUtil.configureSpinner(card_expire_year_et);
//
//        card_expire_year_et.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String yearDate = years.get(i);
//                Log.e("zayedyear", yearDate);
//                year=i;
//                if(year > 1) {
//
//                    Log.e("minhazayedYear2kkkkkkkk", year+"");
//                }
//
//                if (yearDate!=null){
////                    HealthRequestModel2.getInstance().setGrantYear(yearDate);
//                    if (year!=0)
//                    year=Integer.parseInt(years.get(i));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//    }

    void initYear() {
        years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        years.add(getString(R.string.choose));
        for (int i = thisYear; i <= 2030; i++) {
            years.add(Integer.toString(i));
        }
        CustomAdapterSpinner customAdapter = new CustomAdapterSpinner(WebviewActivity.this, years);
        card_expire_year_et.setAdapter(customAdapter);
//        ViewUtil.configureSpinner(card_expire_year_et);

        card_expire_year_et.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String yearDate = years.get(i);
                Log.e("zayedyear", yearDate);
                year=i;
                if(year > 1) {

                    Log.e("minhazayedYear2kkkkkkkk", year+"");
                }

                if (yearDate!=null){
//                    HealthRequestModel2.getInstance().setGrantYear(yearDate);
                    if (year!=0)
                        year=Integer.parseInt(years.get(i));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    void initMonth() {
        months = new ArrayList<String>();
        int thisMounth = Calendar.getInstance().get(Calendar.MONTH);

        months.add(getString(R.string.choose));
        months.add("1");
        months.add("2");
        months.add("3");
        months.add("4");
        months.add("5");
        months.add("6");
        months.add("7");
        months.add("8");
        months.add("9");
        months.add("10");
        months.add("11");
        months.add("12");

//        for (int i = 1; i <= thisMounth; i++) {
//            months.add(Integer.toString(i));
//        }
        CustomAdapterSpinner customAdapter = new CustomAdapterSpinner(WebviewActivity.this, months);
        card_expire_date_et.setAdapter(customAdapter);
//        ViewUtil.configureSpinner(card_expire_date_et);
        card_expire_date_et.setSelection(0);

        card_expire_date_et.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0){
                    String monthDate = months.get(i);
                Log.e("monthDate", monthDate);
                Log.e("iiiiiiiiiii", i+"");
                month = i;
                if (month > 1) {

                    Log.e("minhazayedYear2kkkkkkkk", month + "");
                }

                if (monthDate != null) {
                    if (month != 0)
                        month = Integer.parseInt(months.get(i));
                }
                if (month < getCurrentMonth()) {
                    month = 0;
                    Toast.makeText(WebviewActivity.this, "invalid month", Toast.LENGTH_SHORT).show();
                    card_expire_date_et.setSelection(0);
                }

            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

//    void initMonth() {
//        months = new ArrayList<String>();
//        int thisMounth = Calendar.getInstance().get(Calendar.MONTH);
//
//        for (int i = 1; i <= MONTH; i++) {
//            months.add(Integer.toString(i));
//        }
//        CustomAdapterSpinner customAdapter = new CustomAdapterSpinner(WebviewActivity.this, months);
//        card_expire_date_et.setAdapter(customAdapter);
//        ViewUtil.configureSpinner(card_expire_year_et);
//        card_expire_date_et.setSelection(0);
//        card_expire_date_et.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String monthDate = months.get(i);
//                Log.e("monthDate", monthDate);
//                month=i;
//                if(month > 1) {
//
//                    Log.e("minhazayedYear2kkkkkkkk", month+"");
//                }
//
//                if (monthDate!=null){
////                    HealthRequestModel2.getInstance().setGrantYear(yearDate);
//                    if (month!=0)
//                        month=Integer.parseInt(months.get(i));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//    }


    void clickPayment() {
        Log.e("UserId", PreferenceHelper.getUserID(WebviewActivity.this));
        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validData()){
                    performPayment(new PaymentRequest(92,
                            Long.parseLong(card_number_et.getText().toString())
                    ,month
                    ,year
                    ,Integer.parseInt(card_cvv_et.getText().toString())
                    ,paymentTypestr
                    ,37));
                }
            }
        });
    }

    boolean validData(){
        if (paymentType!=0){
            boolean validCardNumber= validationTool.checkCardNumber(card_number_et,card_number_et.getText().toString());
            if (validCardNumber){
                if (month!=0){

                    if (year!=0){

                        if (!card_cvv_et.getText().toString().isEmpty()){
                            if (card_cvv_et.getText().toString().length()==3){
                                return true;
                            }else {
                                Toast.makeText(this, getString(R.string.card_cvv_error), Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }else {
                            Toast.makeText(this, getString(R.string.card_cvv_empty), Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }else {
                        Toast.makeText(this, getString(R.string.choose_year), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else {
                    Toast.makeText(this, getString(R.string.choose_month), Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else {
                return false;
            }
        }else {
            Toast.makeText(this, getString(R.string.choose_card_type), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    void actionDate(){
//        card_expire_date.setOnClickListener(v -> viewCalender());
//        card_expire_date_et.setOnClickListener(v -> initMonth());
    }

    void viewCalender() {
        ViewUtil.hideKeyboard(WebviewActivity.this);
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(YEAR, year);
//                myCalendar.set(MONTH, monthOfYear);
//                myCalendar.set(DAY_OF_MONTH, dayOfMonth);

                if (myCalendar.getTime().before(new Date())) {
                    updateDateLabel(year, monthOfYear, dayOfMonth);
                } else {
                    Toast.makeText(WebviewActivity.this, getString(R.string.invalid_date), Toast.LENGTH_SHORT).show();
                }
            }
        };
                new DatePickerDialog(WebviewActivity.this, date, myCalendar
                        .get(YEAR), myCalendar.get(MONTH),
                        myCalendar.get(DAY_OF_MONTH)).show();

    }
            private void updateDateLabel(int year, int month, int day) {

                String myFormat = "MM/dd/yyyy";
                String myFormat2 = "dd-MM-yyyy"; //In which you need put her //In which you need put here
                SimpleDateFormat formate2 = new SimpleDateFormat(myFormat2, Locale.ENGLISH);
                SimpleDateFormat formate = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                Log.d("serviceFormat", formate.format(myCalendar.getTime()));

                Date birthDate = null;
                try {
                    birthDate = formate2.parse(formate2.format(myCalendar.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                cardExpireDate = formate.format(myCalendar.getTime());
//                card_expire_date_et.setText(formate2.format(myCalendar.getTime()));

            }

            void handelCheckBoxClick(){
                visa_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if ( isChecked )
                        {
                            visa_chk.setChecked(true);
                            master_chk.setChecked(false);
                            paymentType=1;
                            paymentTypestr= Constants.visa;
                        }else {
                            Log.e("","");
                        }

                    }
                });

                master_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        if ( isChecked )
                        {

                            master_chk.setChecked(true);
                            visa_chk.setChecked(false);
                            paymentType=2;
                            paymentTypestr= Constants.master;
                        }

                    }
                });

            }

            public int getCurrentMonth(){
                int month;
                DateFormat dateFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                Log.d("MonthOfToday",dateFormat.format(date));
                String dateStr = dateFormat.format(date);
                month = Integer.parseInt(dateStr);

                return month;
            }

    void hideKeyboard() {
        card_expire_date_et.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(amount_et.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(card_number_et.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(card_cvv_et.getWindowToken(), 0);
                return false;
            }
        });

        card_expire_year_et.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(amount_et.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(card_number_et.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(card_cvv_et.getWindowToken(), 0);
                return false;
            }
        });

    }
 }


