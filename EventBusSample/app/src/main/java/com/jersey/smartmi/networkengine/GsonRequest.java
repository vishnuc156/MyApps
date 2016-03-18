/*
 * ///////////////////////////////////////////////////////////////////////////
 * //                                                                       //
 * //               EATOUTZ.com Proprietary & Confidential                  //
 * // ______________________________________________________________________//
 * //                                                                       //
 * //               Copyright (C) 2014 - 2015 Eatoutz.com                   //
 * //                                                                       //
 * // NOTICE:  All information contained herein is, and remains the property//
 * // of eatoutz.com and its partners, if any. The intellectual and         //
 * // technical concepts contained herein are proprietary to eatoutz.com and//
 * // its partners and may be covered by U.S. and Foreign Patents, patents  //
 * // in process, and are protected by trade secret or copyright law.       //
 * // Dissemination of this information or reproduction of this material is //
 * // strictly forbidden unless prior written permission is obtained from   //
 * // eatoutz.com.                                                          //
 * ///////////////////////////////////////////////////////////////////////////
 */

package com.jersey.smartmi.networkengine;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jersey.smartmi.utils.CommonData;
import com.jersey.smartmi.utils.Constants;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p/>
 * Class for all JSON Request and using GSON library to convert JSON to POJO
 */
public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private Map<String, String> headers = null;
    private Map<String, String> params = null;
    private final Listener<T> listener;
    private final String url;
    private final String mRequestBody;

    /**
     * Charset for request.
     */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);



    /**
     * make GET requset with authentication header
     * @param url
     * @param clazz
     * @param allowCaching
     * @param listener
     * @param errorListener
     */
    public GsonRequest(String url, Class<T> clazz,
                       boolean allowCaching, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        setShouldCache(allowCaching);
        this.url = url;
        this.clazz = clazz;
        this.headers = CommonData.httpHeaderData();
        this.listener = listener;
        this.mRequestBody = null;
        this.params=null;
/*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIMEOUT,
                Constants.REQUEST_RETRIES,
                Constants.REQUEST_BACKOFF_MULT));
    }
    //for row data
    public GsonRequest(String url,JSONObject rowData, Class<T> clazz,
                       boolean allowCaching, Listener<T> listener, ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        setShouldCache(allowCaching);
        this.url = url;
        this.clazz = clazz;
        this.headers = CommonData.httpHeaderData();
        this.listener = listener;
        this.mRequestBody = rowData.toString();
        this.params = null;
        /*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIMEOUT,
                Constants.REQUEST_RETRIES,
                Constants.REQUEST_BACKOFF_MULT));
    }
//for Login with token
    public GsonRequest(String url, String token, Class<T> clazz,
                       boolean allowCaching, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        setShouldCache(allowCaching);
        this.url = url;
        this.clazz = clazz;
        this.headers = CommonData.httpHeaderData();
        this.listener = listener;
        this.mRequestBody = null;
        this.params=null;
/*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIMEOUT,
                Constants.REQUEST_RETRIES,
                Constants.REQUEST_BACKOFF_MULT));
    }

//    Login with username and password
    public GsonRequest(String url, String userName, String password, Class<T> clazz,
                       boolean allowCaching, Listener<T> listener, ErrorListener errorListener) {
        super(Method.GET, url, errorListener);

        setShouldCache(allowCaching);
        this.url = url;
        this.clazz = clazz;

        // this.headers = CommonData.httpHeaderDataWithToken(token);
        this.listener = listener;
        this.mRequestBody = null;
        this.params=null;
/*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIMEOUT,
                Constants.REQUEST_RETRIES,
                Constants.REQUEST_BACKOFF_MULT));
    }
    /**
     * make PoSt requset with authentication header
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public GsonRequest(String url, Class<T> clazz,String mRequestBody,
                      Listener<T> listener, ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        setShouldCache(false);
        this.url = url;
        this.clazz = clazz;
        this.headers = CommonData.httpHeaderData();
        this.listener = listener;
        this.mRequestBody = mRequestBody;
        this.params=null;
/*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIMEOUT,
                Constants.REQUEST_RETRIES,
                Constants.REQUEST_BACKOFF_MULT));
    }



    public GsonRequest(int method, String url, Class<T> clazz, String mUrlParams,
                       boolean allowCaching, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        setShouldCache(allowCaching);
        this.url = url;
        this.clazz = clazz;
        this.headers = CommonData.httpHeaderData();
        this.listener = listener;
        this.mRequestBody = null;
       // this.params=CommonData.deleteBulletinRequestBody(mUrlParams);
        /*--------------Added for avoid timeout error-------------------*/
        setRetryPolicy(new DefaultRetryPolicy(
               Constants.REQUEST_TIMEOUT,
             Constants.REQUEST_RETRIES,
              Constants.REQUEST_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {

            String json =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.e("RESPONSE FROM SERVER", json);


            return Response.success(gson.fromJson(json, clazz),
                                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            CommonData.showThreeLog("Exception", e.getMessage(), e);
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            CommonData.showThreeLog("Exception", e.getMessage(), e);
            return Response.error(new ParseError(e));
        }
    }


    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            CommonData.showThreeLog("Exception", e.getMessage(), e);
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                          mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }
}