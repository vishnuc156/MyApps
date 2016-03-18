package com.jersey.smartmi.fragments.view_quotes_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jersey.smartmi.R;
import com.jersey.smartmi.adapter.recycler_adapter.ViewQuotesAdapter;
import com.jersey.smartmi.appcontroller.AppController;
import com.jersey.smartmi.models.error_parser.ErrorParser;
import com.jersey.smartmi.models.view_quotes_response_parser.Quotes;
import com.jersey.smartmi.models.view_quotes_response_parser.ViewQuotesResponseParser;
import com.jersey.smartmi.networkengine.OwnerRequestManager;
import com.jersey.smartmi.widget.CustomAlertDialogue;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by Vishnu on 24/02/16.
 */
public class ViewQuotesFragment extends Fragment {
    private RecyclerView mRecylclerViewQuotes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_quotes_fragment, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_quote);
        //set toolbar appearance
        //toolbar.setBackground(R.color.material_blue_grey_800);

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mRecylclerViewQuotes = (RecyclerView) rootView.findViewById(R.id.recylclerViewQuotes);
        mRecylclerViewQuotes.setHasFixedSize(true);
        // use a linear layout manager
        mRecylclerViewQuotes.setLayoutManager(new LinearLayoutManager(getActivity()));

        getAllQuotesList();
        return rootView;
    }

    public void getAllQuotesList() {
        OwnerRequestManager.getInstance(getActivity()).requestQuoteList();
    }

    @Subscribe
    public void setAllQuotesList(ViewQuotesResponseParser responseParser) {
        ArrayList<Quotes> responseQuotesList = responseParser.content.quotes;
        ViewQuotesAdapter adapter = new ViewQuotesAdapter(getActivity(), responseQuotesList);
        mRecylclerViewQuotes.setAdapter(adapter);
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
