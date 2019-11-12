package com.example.misdaqia.SharedPreferences;


import android.content.Context;

import com.google.gson.Gson;

public class PreferenceHelper {

    public static String TAG_LANGUAGE_SCREEN = "language";
    public static String TAG_LOCALIZATION = "localization";
    public static String TAG_SAVE_USER_ID = "user_id";
    public static String TAG_SAVE_USER_Image = "user_image";
    public static String TAG_SAVE_USER_Full_name = "user_full";
    public static String TAG_SAVE_USER_PHONE = "user_phone";
    public static String TAG_REMEMBER_ME = "remmeber_me";
    public static String TAG_IS_USER = "is_user";
    public static String TAG_Auth_token = "auth_token";
    public static String TAG_Auth_token_Guest = "auth_token_guest";
    public static String TAG_EMAIL = "user_email";
    public static String TAG_FIRST = "first_name";
    public static String TAG_FIRST_INSTALL = "first_install";

    public static String TAG_TOKEN_USER = "login_token";

    public static String TAG_CODE = "user_code";

    public static String TAG_PHONE = "user_phone_setting";

    public static String TAG_USER_IMAGE = "user_image";
    public static String TAG_USER_NAME = "user_name";
    public static String TAG_USER_EMAIL = "user_email";

    public static String TAG_TALAB_KIND = "talab_kind";
    public static String TAG_TALAB_EDUCATION = "talab_kind_education";
    public static String TAG_USER_ID = "tag_user_id";

    public static String TAG_OF_USER = "user_id";
    public static String TAG_OF_IMAGE_KIND = "image_kind";

    public static String TAG_OF_ALL_LIST = "all_list";

    public static String TAG_IS_NOTIFICATION = "is_notification";

    public static int getImageKind(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_OF_IMAGE_KIND);
    }

    public static void setImageKind(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_OF_IMAGE_KIND, value);
    }


    public static void setUserIdAfterLogin(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_OF_USER, value);
    }

    public static String getUserIdAfterLogin(Context context) {
        return SharedPreferencesTool.getString(context, TAG_OF_USER);
    }

    public static void setUsetId(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_USER_ID, value);
    }

    public static int getUserId(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_USER_ID);
    }

    public static void saveTalabKindEducation(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_TALAB_EDUCATION, value);
    }

    public static int getTalabKindEducation(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_TALAB_EDUCATION);
    }

    public static void saveTalabKind(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_TALAB_KIND, value);
    }

    public static int getTalabKind(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_TALAB_KIND);
    }

    public static void saveLocalizationID(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_LOCALIZATION, value);
    }

    public static int getLocalizationID(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_LOCALIZATION);
    }




    public static void setTokenUserID(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_TOKEN_USER, value);
    }

    public static String getTokenUserID(Context context) {
        return SharedPreferencesTool.getString(context, TAG_TOKEN_USER);
    }




    public static void saveUserID(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_SAVE_USER_ID, value);
    }

    public static String getUserID(Context context) {
        return SharedPreferencesTool.getString(context, TAG_SAVE_USER_ID);
    }

    public static void saveProfileImage(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_SAVE_USER_Image, value);
    }

    public static String getProfileImage(Context context) {
        return SharedPreferencesTool.getString(context, TAG_SAVE_USER_Image);
    }

    public static void saveUserFullName(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_SAVE_USER_Full_name, value);
    }
    public static void saveEmail(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_EMAIL, value);
    }

    public static String getUserFullName(Context context) {
        return SharedPreferencesTool.getString(context, TAG_SAVE_USER_Full_name);
    }
    public static String getUserFirstName(Context context) {
        return SharedPreferencesTool.getString(context, TAG_FIRST);
    }

    public static void setUserFirstName(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_FIRST, value);
    }

    public static String getUserEmail(Context context) {
        return SharedPreferencesTool.getString(context, TAG_EMAIL);
    }

    public static void saveUserPhone(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_SAVE_USER_PHONE, value);
    }

    public static String getUserPhone(Context context) {
        return SharedPreferencesTool.getString(context, TAG_SAVE_USER_PHONE);
    }

    public static boolean getRemmemberMe(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_REMEMBER_ME);
    }

    public static void setRemmemberMe(Context context, boolean rememberMe) {
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_REMEMBER_ME);
    }

    public static String getAuthToken(Context context) {
        return SharedPreferencesTool.getString(context, TAG_Auth_token);
    }

    public static void saveAuthToken(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_Auth_token, value);

    }

    public static String getTokenForUser(Context context) {
        return SharedPreferencesTool.getString(context, TAG_Auth_token);
    }

    public static void setTokenForUser(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_Auth_token, value);

    }

    public static String getTokenForGuest(Context context) {
        return SharedPreferencesTool.getString(context, TAG_Auth_token_Guest);
    }

    public static void setTokenForGuest(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_Auth_token_Guest, value);
    }



    public static void clearShared(Context context) {
        SharedPreferencesTool.clearObject(context);
    }

    public void setLanguageScreen(Context context, boolean isShown) {
        SharedPreferencesTool.setBoolean(context, isShown, TAG_LANGUAGE_SCREEN);
    }

    public boolean isLanguageShown(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_LANGUAGE_SCREEN);
    }

    public static String getCurrentLang(Context context) {
        return  SharedPreferencesTool.getString(context, TAG_LANGUAGE_SCREEN);
    }


    public static void saveCurrentLanguage(Context context, String Lang) {
        SharedPreferencesTool.saveObject(context, TAG_LANGUAGE_SCREEN, Lang);
    }
    public static void saveNotFirstInstall(Context context, boolean state) {
        SharedPreferencesTool.setBoolean(context, state, TAG_FIRST_INSTALL);
    }

    public static Boolean ISNotFirstInstall(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_FIRST_INSTALL);
    }


    //user

    public static int getCode(Context context) {
        return SharedPreferencesTool.getInt(context, TAG_CODE);
    }

    public static void setCode(Context context, int value) {
        SharedPreferencesTool.setInt(context, TAG_CODE, value);

    }

    public static String getPhone(Context context) {
        return SharedPreferencesTool.getString(context, TAG_PHONE);
    }

    public static void setPhone(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_PHONE, value);

    }

    public static String getUserImage(Context context) {
        return SharedPreferencesTool.getString(context, TAG_USER_IMAGE);
    }

    public static void setUserImage(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_USER_IMAGE, value);
    }

    public static String getUserName(Context context) {
        return SharedPreferencesTool.getString(context, TAG_USER_NAME);
    }

    public static void setUserName(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_USER_NAME, value);
    }

    public static String getUserEmailAddress(Context context) {
        return SharedPreferencesTool.getString(context, TAG_USER_EMAIL);
    }

    public static void setUserEmailAddress(Context context, String value) {
        SharedPreferencesTool.setString(context, TAG_USER_EMAIL, value);
    }

    public static boolean getIsGuest(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_IS_USER);
    }

    public static void setIsGuest(Context context, boolean rememberMe) {
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_IS_USER);
    }

    public static boolean getNotificationState(Context context) {
        return SharedPreferencesTool.getBoolean(context, TAG_IS_NOTIFICATION);
    }

    public static void setNotificationStatet(Context context, boolean rememberMe) {
        SharedPreferencesTool.setBoolean(context, rememberMe, TAG_IS_NOTIFICATION);
    }
}
