/*
 * <!--
 *   ~ ///////////////////////////////////////////////////////////////////////////
 *   ~ //                                                                       //
 *   ~ //               EATOUTZ.com Proprietary & Confidential                  //
 *   ~ // ______________________________________________________________________//
 *   ~ //                                                                       //
 *   ~ //               Copyright (C) 2014 - 2015 Eatoutz.com                   //
 *   ~ //                                                                       //
 *   ~ // NOTICE:  All information contained herein is, and remains the property//
 *   ~ // of eatoutz.com and its partners, if any. The intellectual and         //
 *   ~ // technical concepts contained herein are proprietary to eatoutz.com and//
 *   ~ // its partners and may be covered by U.S. and Foreign Patents, patents  //
 *   ~ // in process, and are protected by trade secret or copyright law.       //
 *   ~ // Dissemination of this information or reproduction of this material is //
 *   ~ // strictly forbidden unless prior written permission is obtained from   //
 *   ~ // eatoutz.com.                                                          //
 *   ~ ///////////////////////////////////////////////////////////////////////////
 *   -->
 */

package com.jersey.smartmi.networkengine;

import android.content.Context;

import com.android.volley.Request;
import com.jersey.smartmi.appcontroller.AppController;
import com.jersey.smartmi.utils.CommonData;

import com.jersey.smartmi.widget.CustomAlertDialogue;

/**
 * Created by InApp on 19/02/16
 * <p/>
 * Core class to create RequestQueue and Manage RequestChain
 */
public class OwnerRequestManager {

    private static final String TAG = OwnerRequestManager.class.getSimpleName();
    private static OwnerRequestManager instance;
    private static Context context;
    private static volatile boolean flag = false;


    public static OwnerRequestManager getInstance(Context context) {
        if (!flag) {
            synchronized (OwnerRequestManager.class) {
                if (!flag) {
                    instance = new OwnerRequestManager();
                    flag = true;
                }
            }
        }
        OwnerRequestManager.context = context;
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        AppController.getInstance().getRequestQueue().add(req);

    }

    public <T> void addOkHttpToRequestQueue(Request<T> req) {
        AppController.getInstance().getOkHttpRequestQueue().add(req);
    }

    public void requestLogin(String userName, String password) {
        if (CommonData.isNetworkConnected(context))
            addToRequestQueue(BaseAPIRequest.getInstance(context).loginRequest(userName, password));
        else
            CustomAlertDialogue.simpleAlertDialogue(context, "");
    }

    public void requestQuoteList() {
        if (CommonData.isNetworkConnected(context))
            addToRequestQueue(BaseAPIRequest.getInstance(context).QuoteListRequest());
        else
            CustomAlertDialogue.simpleAlertDialogue(context, "");

    }

    public void requestQuoteDetails(String quote_Id) {
        if (CommonData.isNetworkConnected(context))
            addToRequestQueue(BaseAPIRequest.getInstance(context).QuoteDetailsRequest(quote_Id));
        else
            CustomAlertDialogue.simpleAlertDialogue(context, "");

    }
}