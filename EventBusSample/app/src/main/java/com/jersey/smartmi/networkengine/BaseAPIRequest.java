package com.jersey.smartmi.networkengine;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jersey.smartmi.appcontroller.AppController;
import com.jersey.smartmi.models.login_response_parser.LoginResponse;
import com.jersey.smartmi.models.quote_details_response_parser.QuoteDetailsResponseParser;
import com.jersey.smartmi.models.view_quotes_response_parser.ViewQuotesResponseParser;
import com.jersey.smartmi.utils.NetworkData;


/**
 * Created by Vishnu on 04/11/15.
 */
public class BaseAPIRequest implements Response.ErrorListener, Response.Listener {
    private static BaseAPIRequest ourInstance = new BaseAPIRequest();
    private static Context context;
    //Dialog pdialog;
    ProgressDialog pdialog;

    public static BaseAPIRequest getInstance(Context con) {
        context = con;
        return ourInstance;
    }

    private BaseAPIRequest() {

    }

    public void ShowDialog(Context con) {
        //  pdialog= CustomAlertDialogue.customProgressdialogue(con);
        pdialog = new ProgressDialog(con);
        //pdialog.setMessage(con.getResources().getString(R.string.loading));
        pdialog.setCancelable(false);
        if (pdialog.isShowing())
            pdialog.dismiss();
        pdialog.show();
    }

    public Request loginRequest(String userName, String password) {
        ShowDialog(context);
        return new GsonRequest(NetworkData.LOGIN_API, userName, password, LoginResponse.class, false, this, this);
    }

    public Request QuoteListRequest() {
        ShowDialog(context);
        Log.d("DeviceListRequest", NetworkData.BASE_URL);
        return new GsonRequest(NetworkData.VIEW_QUOTES_API, ViewQuotesResponseParser.class, false, this, this);
    }

    public Request QuoteDetailsRequest(String quote_Id) {
        ShowDialog(context);
        return new GsonRequest(NetworkData.VIEW_QUOTE_DETAILS_API, QuoteDetailsResponseParser.class, false, this, this);
    }

    @Override
    public void onResponse(Object response) {
        pdialog.dismiss();

        if (response instanceof LoginResponse) {
            AppController.getEventBus().post((LoginResponse) response);
        } else if (response instanceof ViewQuotesResponseParser) {
            AppController.getEventBus().post((ViewQuotesResponseParser) response);
        } else if (response instanceof QuoteDetailsResponseParser) {
            AppController.getEventBus().post((QuoteDetailsResponseParser) response);
        }


    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
//        String json = volleyError.getMessage();
        pdialog.dismiss();
        AppController.getEventBus().post(volleyError);
    }
}
