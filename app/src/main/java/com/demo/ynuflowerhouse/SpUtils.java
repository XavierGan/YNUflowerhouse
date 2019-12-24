package com.demo.ynuflowerhouse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;



public class SpUtils {

    @SuppressLint("WrongConstant")
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }


    public static String getString(Context context, String key) {
        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_APPEND);
        return sharedPreferences.getString(key, "");
    }
}
