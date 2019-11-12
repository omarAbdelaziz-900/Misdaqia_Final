package com.example.misdaqia.Utils;

import android.content.Context;
import android.widget.EditText;

import com.example.misdaqia.Helper.MainFontEdittext;
import com.example.misdaqia.Helper.MainFontTextview;
import com.example.misdaqia.R;

public class ValidationTool {

    private  Context context;

    public ValidationTool(Context context) {
        this.context = context;
    }

    public static boolean checkIsNumber(String content) {
        String regex = "[0-15]+";
        if (content.matches(regex)) {
            if (content.length() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public  boolean checkCardNumber(MainFontEdittext editText , String cardNumber){
//        int cardNum=Integer.parseInt(cardNumber);
//        if (cardNumber.length()>0){
        if (!cardNumber.isEmpty()){
            if (cardNumber.length()==16){
                return true;
            }else {
                editText.setError(context.getResources().getString(R.string.card_number_error));
                return false;
            }
        }else {
            editText.setError(context.getResources().getString(R.string.card_number_empty));
            return false;
        }
    }
}
