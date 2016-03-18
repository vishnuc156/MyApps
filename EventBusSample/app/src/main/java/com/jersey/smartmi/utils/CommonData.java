package com.jersey.smartmi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vishnu on 12/02/16.
 */
public class CommonData {
    public static void showLogE(String tag, String data) {
            Log.e(tag, data);
    }
    public static void showThreeLog(String tag, String data, Exception e) {
            Log.d(tag, data,e);
    }
    public static void showLogD(String tag, String data) {
            Log.d(tag, data);
    }

    public static void showLogI(String tag, String data) {
            Log.i(tag, data);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    public static Map<String, String> httpHeaderDataWithToken(String token) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Content-Accept", "application/json");
        headers.put("API-Token", token);
        return headers;
    }

    public static Map<String, String> httpHeaderData() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Content-Accept", "application/json");
        //headers.put("API-Token", CommonData.MOBI_TOKEN);
        return headers;

    }
}
