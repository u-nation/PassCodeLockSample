package com.example.u_nation.passcodelocksample.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.u_nation.passcodelocksample.MyApplication;

public class PrefUtil {

    private static SharedPreferences shPref;
    private static SharedPreferences.Editor editor;

    private static SharedPreferences.Editor getEditor() {
        if (editor == null) editor = getSharedPreferences().edit();
        return editor;
    }

    private static SharedPreferences getSharedPreferences() {
        if (shPref == null) shPref = MyApplication.getInstance().getSharedPreferences(MyApplication.getInstance().getPackageName(), Context.MODE_PRIVATE);
        return shPref;
    }

    public static void setSharedPreferences(Context context) {
        if (shPref == null) shPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * @param key default -1
     */
    public static int getInt(String key) {
        return getSharedPreferences().getInt(key, -1);
    }

    /**
     * @param key default -1L
     */
    public static long getLong(String key) {
        return getSharedPreferences().getLong(key, -1L);
    }

    /**
     * @param key default false
     */
    public static boolean getBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }

    /**
     * @param key default ""
     */
    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }


    public static void putInt(String key, int value) {
        getEditor().putInt(key, value).apply();
    }

    public static void putLong(String key, long value) {
        getEditor().putLong(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public static void putString(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public static boolean isEmpty(String key) {
        return getString(key).equals("");
    }

    public static synchronized void removeValue(String key) {
        getEditor().remove(key).apply();
    }
}