package com.jersey.smartmi.listeners;

import android.app.Fragment;

/**
 * Created by Vishnu on 29/02/16.
 */
public interface FragmentChangeListener {
    void callQuoteDetails(String from, String quote_Id, Fragment toFragment);
}
