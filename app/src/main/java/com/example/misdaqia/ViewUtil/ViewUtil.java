package com.example.misdaqia.ViewUtil;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.misdaqia.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;


import static android.support.v4.text.HtmlCompat.FROM_HTML_MODE_LEGACY;


public final class ViewUtil {

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

//    public static void showCustomToast(Context context, int resource_id) {
//        Toast toast = Toast.makeText(context, context.getString(resource_id), Toast.LENGTH_LONG);
//        View toastView = toast.getView(); // This'll return the default View of the Toast.
//
//        /* And now you can get the TextView of the default View of the Toast. */
//        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
////        toastMessage.setTextSize(25);
//        toastMessage.setTextColor(Color.WHITE);
//        toastMessage.setGravity(Gravity.CENTER);
//        toastMessage.setPadding(30, 4, 30, 4);
//        toastView.setBackgroundColor(context.getResources().getColor(R.color.color_main_text));
//        toast.show();
//    }

//    public static void showCustomToast(Context context, String message) {
//        Toast toast = Toast.makeText(context, "" + message, Toast.LENGTH_LONG);
//        View toastView = toast.getView(); // This'll return the default View of the Toast.
//
//        /* And now you can get the TextView of the default View of the Toast. */
//        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
////        toastMessage.setTextSize(25);
//        toastMessage.setTextColor(Color.WHITE);
//        toastMessage.setGravity(Gravity.CENTER);
//        toastMessage.setPadding(30, 4, 30, 4);
//        toastView.setBackgroundColor(context.getResources().getColor(R.color.color_main_text));
//        toastMessage.setBackgroundColor(context.getResources().getColor(R.color.color_main_text));
//        toast.show();
//    }
    /*public static void makeCorneredImage(){
        BitmapShader shader;
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rect = new RectF(0.0f, 0.0f, width, height);

// rect contains the bounds of the shape
// radius is the radius in pixels of the rounded corners
// paint contains the shader that will texture the shape
        canvas.drawRoundRect(rect, radius, radius, paint);
    }*/


    public static String formatDecimal(double doubleValue){
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(doubleValue);
    }
    public static String formatDecimalRemovePoints(double doubleValue){
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(doubleValue);
    }
//    public static String getFinalDiscount(Context context, double price) {
//        return formatDecimal(price) + "% " + context.getString(R.string.off);
//    }


    public static boolean canConnect(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

//    public static void configToasty(Context context) {
////        Toasty.Config.getInstance()
////                .setErrorColor(getResources().getColor(R.color.dark_blue))
////                .setInfoColor()
////                .setSuccessColor()
////                .setWarningColor(getResources().getColor(R.color.dark_blue))
////                .setTextColor()
////                .setToastTypeface() // optional
////                .setTextSize(14)
////                .apply();
//        Toasty.Config.getInstance()
//                .setErrorColor(context.getResources().getColor(R.color.color_green))
//                .setSuccessColor(context.getResources().getColor(R.color.color_green))
//                .setWarningColor(context.getResources().getColor(R.color.color_brown))
//                .setTextColor(context.getResources().getColor(R.color.color_white))
//                .setTextSize(13)
//                .apply();
////        Toasty.Config.getInstance().setErrorColor(getResources().getColor(R.color.dark_blue)).apply();
//    }
//    public static void showToast( Context context,String message) {
//        Toasty.warning( context,message, Toast.LENGTH_SHORT, true).show();
//
//    }
//
//    public static void showToastLong( Context context,String message) {
//        Toasty.warning( context,message, Toast.LENGTH_LONG, true).show();
//
//    }

//    public static void configureSpinner(Spinner spinner) {
//        spinner.setScrollContainer(true);
////        spinner.setBackgroundResource(0);
////        spinner.setDropDownWidth(200);
//        spinner.setPadding(50,50,0,50);
//        spinner.setPopupBackgroundResource(R.color.light_brown);
//        spinner.setPaddingRelative(20, 20, 20, 20);
//    }
//    public static void configureSpinnerGreen(Spinner spinner) {
//        spinner.setScrollContainer(true);
////        spinner.setDropDownWidth(200);
////        spinner.setPopupBackgroundResource(R.color.green_bg_spinner);
//        spinner.setPopupBackgroundResource(R.color.green_bg_spinner);
//        spinner.setPaddingRelative(20, 20, 20, 20);
//    }

    public static void openWebPage(Context context, String url) {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            Uri webPage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
            context.startActivity(intent);
        } catch (Exception ex) {
        }
    }

    public static void getOpenFacebookIntent(Context context,String url) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            Log.e("done","done");
//            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://"+url));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://www."+url));
            context.startActivity(intent);

        } catch (Exception e) {
            Log.e("notdone","notdone");
//            return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }
    }

    public static void newFacebookIntent(Context context , PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                Log.e("done","done");
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
            openWebPage(context,url);
        }
//        return new Intent(Intent.ACTION_VIEW, uri);

    }

//    public static void launchUserINformation(Context context, boolean isAdded) {
//        Intent intent = new Intent(Constants.ACTIIN_ADD_USER_INFO);
//        intent.putExtra(Constants.MESSAGE, isAdded);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//    }

    public static void putPrefMobile(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPrefMobile(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    private int getScreenOrientation(Activity context) {
        int rotation = context.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

//                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

//                    orientation =
//                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    Log.e("TAG", "Unknown screen orientation. Defaulting to " +
                            "portrait.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch(rotation) {
                case Surface.ROTATION_0:
//                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
//                    orientation =
//                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    Log.e("TAG", "Unknown screen orientation. Defaulting to " +
                            "landscape.");
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

    //stop scroll view to make action on edittext
    public static void initScrollView(final ScrollView scrollView){
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    v.requestFocusFromTouch();
//                }
                if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    v.requestFocusFromTouch();
                }
//
                return false;
            }
        });
    }

    public static void hideKeyBoard(Context context,View view) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void getHtmlText(String text,TextView textView) {
        String textStr = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, FROM_HTML_MODE_LEGACY));
//            textView.setText(Html.fromHtml(text));
            Log.e("firstCondition","firstCondition");
//            textStr = String.valueOf(sp);
        } else {
//            Spanned sp = Html.fromHtml(text);
            Log.e("secondCondition","secondCondition");
            textView.setText(Html.fromHtml(text));
//            textStr = String.valueOf(sp);

        }
//        textView.setText(textStr);
    }


    public static String convertNumbersToEnglish(String str){
        str = str.replace("٠","0")
                .replace("١","1")
                .replace("٢","2")
                .replace("٣","3")
                .replace("٤","4")
                .replace("٥","5")
                .replace("٦","6")
                .replace("٧","7")
                .replace("٨","8")
                .replace("٩","9");


        Log.e("Number in English",str);

        return str;
    }

    public static void openDialog(ProgressDialog progressDialog,Context context,int type){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.please_wait));
        progressDialog.setCancelable(false);
//        type 1 //show
//        type 2 //dismiss
        if (type==1){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }
    public static void configureSpinner(Spinner spinner) {
        spinner.setScrollContainer(true);
//        spinner.setBackgroundResource(0);
//        spinner.setDropDownWidth(200);
        spinner.setPadding(50,50,0,50);
        spinner.setPopupBackgroundResource(R.color.light_brown);
        spinner.setPaddingRelative(20, 20, 20, 20);
    }
    public static void configureSpinnerGreen(Spinner spinner) {
        spinner.setScrollContainer(true);
//        spinner.setDropDownWidth(200);
//        spinner.setPopupBackgroundResource(R.color.green_bg_spinner);
        spinner.setPopupBackgroundResource(R.color.green_bg_spinner);
        spinner.setPaddingRelative(20, 20, 20, 20);
    }

}
