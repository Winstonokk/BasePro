package com.barnettwong.basepro.mvp.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.barnettwong.basepro.app.WEApplication;


public class PreferenceCache {
    public static final String PF_SP_MAIN = "pf_sp_main";
    public static final String PF_TOKEN = "token"; // token
    public static final String PF_PHONE = "phone"; // phone
    public static final String PF_PWD = "pwd"; // pwd

    private static SharedPreferences getSharedPreferences() {
        return WEApplication.getAppContext().getSharedPreferences(PF_SP_MAIN, Context.MODE_PRIVATE);
    }

    public static void putPhone(String phone) {
        SharedPreferences pref = getSharedPreferences();

        Editor editor = pref.edit();
        editor.putString(PF_PHONE, phone);
        editor.commit();
    }

    public static String getPhone() {
        return getSharedPreferences().getString(PF_PHONE, "");
    }

    public static void deletePhone() {
        SharedPreferences pref = getSharedPreferences();
        Editor editor = pref.edit();
        editor.remove(PF_PHONE);
        editor.commit();
    }

    public static void putToken(String token) {
        SharedPreferences pref = getSharedPreferences();

        Editor editor = pref.edit();
        editor.putString(PF_TOKEN, token);
        editor.commit();
    }

    public static String getToken() {
        return getSharedPreferences().getString(PF_TOKEN, "");
    }

    public static void deleteToken() {
        SharedPreferences pref = getSharedPreferences();
        Editor editor = pref.edit();
        editor.remove(PF_TOKEN);
        editor.commit();
    }

    public static void putPwd(String pwd) {
        SharedPreferences pref = getSharedPreferences();

        Editor editor = pref.edit();
        editor.putString(PF_PWD, pwd);
        editor.commit();
    }

    public static String getPwd() {
        return getSharedPreferences().getString(PF_PWD, "");
    }

    public static void deletePwd() {
        SharedPreferences pref = getSharedPreferences();
        Editor editor = pref.edit();
        editor.remove(PF_PWD);
        editor.commit();
    }


}
