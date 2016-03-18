package com.jersey.smartmi.fragments.view_quotes_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jersey.smartmi.R;
import com.jersey.smartmi.appcontroller.AppController;
import com.jersey.smartmi.models.error_parser.ErrorParser;
import com.jersey.smartmi.models.quote_details_response_parser.QuoteDetailsResponseParser;
import com.jersey.smartmi.networkengine.OwnerRequestManager;
import com.jersey.smartmi.widget.CustomAlertDialogue;
import com.squareup.otto.Subscribe;

/**
 * Created by Vishnu on 29/02/16.
 */
public class ViewQuoteDetailsFragment extends Fragment {
    private String quote_Id;
    private TextView tvQuteRef;
    private TextView tvCustName;
    private TextView tvProjectName;
    private TextView tvSalesEngineer;
    private TextView tvQuotationDate;
    private TextView tvQuoteStatus;
    private TextView tvProductDetailsValue;
    private TextView tvQuoteValue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_quote_detials_fragment, container, false);
        if (getArguments() != null) {
            String quote_Id = getArguments().getString("quote_Id");
        }
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_quote_details);
        tvQuteRef = (TextView) rootView.findViewById(R.id.tvQuteRef);
        tvCustName = (TextView) rootView.findViewById(R.id.tvCustName);
        tvProjectName = (TextView) rootView.findViewById(R.id.tvProjectName);
        tvSalesEngineer = (TextView) rootView.findViewById(R.id.tvSalesEngineer);
        tvQuotationDate = (TextView) rootView.findViewById(R.id.tvQuotationDate);
        tvQuoteStatus = (TextView) rootView.findViewById(R.id.tvQuoteStatus);
        tvProductDetailsValue = (TextView) rootView.findViewById(R.id.tvProductDetailsValue);
        tvQuoteValue = (TextView) rootView.findViewById(R.id.tvQuoteValue);

        getQuoteDetails();
        return rootView;
    }

    public void getQuoteDetails() {
        OwnerRequestManager.getInstance(getActivity()).requestQuoteDetails(quote_Id);
    }

    @Subscribe
    public void setQuotesDetails(QuoteDetailsResponseParser responseParser) {
        setDatatoView(responseParser);
    }
public void setDatatoView(QuoteDetailsResponseParser responseParser){
    tvQuteRef.setText(responseParser.content.quote.get(0).quoteReference);
    tvCustName.setText(responseParser.content.quote.get(0).customerName);
    tvProjectName.setText(responseParser.content.quote.get(0).projectName);
    tvSalesEngineer.setText(responseParser.content.quote.get(0).salesEngineer);
    tvQuotationDate.setText(responseParser.content.quote.get(0).quotationDate);
    tvQuoteStatus.setText(responseParser.content.quote.get(0).quoteStatus);
    tvProductDetailsValue.setText(responseParser.content.quote.get(0).quoteValue);
    tvQuoteValue.setText(responseParser.content.quote.get(0).quoteValue);
}
    @Subscribe
    public void getVolleyError(VolleyError errorResponse) {
        if (errorResponse.networkResponse != null) {
            if (errorResponse.networkResponse.data != null) {
                String errorResponseBody = new String(errorResponse.networkResponse.data);
                Gson gson = new Gson();
                ErrorParser errorParser = gson.fromJson(errorResponseBody, ErrorParser.class);
                if (errorParser != null && errorParser.error_description != null)
                    CustomAlertDialogue.customErrordialogue(getActivity(), errorParser.error_description);
            }
        } else
            CustomAlertDialogue.simpleAlertDialogue(getActivity(), "");
    }

    @Override
    public void onStart() {
        super.onStart();
        AppController.getEventBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        AppController.getEventBus().unregister(this);
    }


}
