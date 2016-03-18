package com.jersey.smartmi.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.jersey.smartmi.R;
import com.jersey.smartmi.fragments.view_quotes_fragment.ViewQuotesFragment;
import com.jersey.smartmi.listeners.FragmentChangeListener;
import com.jersey.smartmi.widget.MyScrollView;
import com.nineoldandroids.view.ViewHelper;

public class SmartMiActivity extends AppCompatActivity implements FragmentChangeListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private LinearLayout layoutDrawer;
    private LinearLayout layoutQuotes;
    private LinearLayout layoutSchedule;
    private LinearLayout layoutTasks;
    private LinearLayout layoutKpiDash;
    private LinearLayout layoutAging;
    private LinearLayout layoutAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_mi);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        layoutDrawer = (LinearLayout) findViewById(R.id.layoutDrawer);

        layoutQuotes = (LinearLayout) findViewById(R.id.layoutQuotes);
        layoutSchedule = (LinearLayout) findViewById(R.id.layoutSchedule);
        layoutTasks = (LinearLayout) findViewById(R.id.layoutTasks);
        layoutKpiDash = (LinearLayout) findViewById(R.id.layoutKpiDash);
        layoutAging = (LinearLayout) findViewById(R.id.layoutAging);
        layoutAddLocation = (LinearLayout) findViewById(R.id.layoutAddLocation);
        layoutQuotes.setOnClickListener(this);
        layoutSchedule.setOnClickListener(this);
        layoutTasks.setOnClickListener(this);
        layoutKpiDash.setOnClickListener(this);
        layoutAging.setOnClickListener(this);
        layoutAddLocation.setOnClickListener(this);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }

    }

    @Override
    public void callQuoteDetails(String from, String quote_Id, Fragment toFragment) {
        setQuoteDetailsFragmetView(toFragment, from, quote_Id);
    }

    public void setQuoteDetailsFragmetView(Fragment fr, String fragmentName, String quote_Id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        //fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.frame_container, fr);
        Bundle bundle = new Bundle();
        bundle.putString("quote_Id", quote_Id);
        fr.setArguments(bundle);
        fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ViewQuotesFragment();
                break;
            case 1:
                fragment = new ViewQuotesFragment();
                break;
            case 2:
                fragment = new ViewQuotesFragment();
                break;
            case 3:
                fragment = new ViewQuotesFragment();
                break;
            case 4:
                fragment = new ViewQuotesFragment();
                break;
            case 5:
                fragment = new ViewQuotesFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            mDrawerLayout.closeDrawer(layoutDrawer);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void setFragmetView(Fragment fr, String fragmentName) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        //fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.frame_container, fr);
        fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutQuotes:
                displayView(0);
                break;
            case R.id.layoutSchedule:
                displayView(1);
                break;
            case R.id.layoutTasks:
                displayView(2);
                break;
            case R.id.layoutKpiDash:
                displayView(3);
                break;
            case R.id.layoutAging:
                displayView(4);
                break;
            case R.id.layoutAddLocation:
                displayView(5);
                break;

            default:
                break;
        }
    }
}
