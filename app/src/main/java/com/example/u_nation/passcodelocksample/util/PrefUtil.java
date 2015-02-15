package com.example.u_nation.passcodelocksample.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtil {
    public static SharedPreferences shPref;
    public static SharedPreferences.Editor editor;

    @SuppressLint({"CommitPrefEdits"})
    public static synchronized void getInstance(Context context) {
        if ((shPref == null) || (editor == null)) {
            shPref = PreferenceManager.getDefaultSharedPreferences(context);
            editor = shPref.edit();
        }
    }

    public static synchronized String getString(Context context, String key) {
        String str = "";
        try {
            getInstance(context);
            str = shPref.getString(key, "");
        } catch (ClassCastException e) {
            str = "";
        }
        return str;
    }

    public static synchronized int getInt(Context context, String key) {
        int val = 0;
        try {
            getInstance(context);
            val = shPref.getInt(key, 0);
        } catch (ClassCastException e) {
            val = 0;
        }
        return val;
    }

    public static synchronized Float getFloat(Context context, String key) {
        float val = 0.0F;
        try {
            getInstance(context);
            val = shPref.getFloat(key, 0.0F);
        } catch (ClassCastException e) {
            val = 0.0F;
        }
        return Float.valueOf(val);
    }

    public static synchronized Long getLong(Context context, String key) {
        long val = 0L;
        try {
            getInstance(context);
            val = shPref.getLong(key, 0L);
        } catch (ClassCastException e) {
            val = 0L;
        }
        return Long.valueOf(val);
    }

    public static synchronized Boolean getBool(Context context, String key) {
        Boolean val = Boolean.valueOf(false);
        try {
            getInstance(context);
            val = Boolean.valueOf(shPref.getBoolean(key, false));
        } catch (ClassCastException e) {
            val = Boolean.valueOf(false);
        }
        return val;
    }

    public static synchronized void setString(Context context, String key, String value) {
        getInstance(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static synchronized void setInt(Context context, String key, int valueInt) {
        getInstance(context);
        editor.putInt(key, valueInt);
        editor.commit();
    }

    public static synchronized void setFloat(Context context, String key, Float valueFloat) {
        getInstance(context);
        editor.putFloat(key, valueFloat.floatValue());
        editor.commit();
    }

    public static synchronized void setLong(Context context, String key, Long valueLong) {
        getInstance(context);
        editor.putLong(key, valueLong.longValue());
        editor.commit();
    }

    public static synchronized void setBool(Context context, String key, boolean bool) {
        getInstance(context);
        editor.putBoolean(key, bool);
        editor.commit();
    }

    public static synchronized void removeValue(Context context, String key) {
        getInstance(context);
        editor.remove(key).commit();
    }

    public static synchronized void clearAllPreferences(Context context, String key) {
        getInstance(context);
        shPref.edit().clear().commit();
    }
}