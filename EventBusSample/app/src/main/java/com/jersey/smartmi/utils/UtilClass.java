package com.jersey.smartmi.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by inapp on 24/02/16.
 */
public class UtilClass {
    public static final synchronized void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager im = (InputMethodManager) context
                    .getApplicationContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(context.getWindow().getDecorView()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
