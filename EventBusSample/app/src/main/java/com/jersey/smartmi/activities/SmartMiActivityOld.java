package com.jersey.smartmi.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.jersey.smartmi.R;
import com.jersey.smartmi.adapter.NavDrawerListAdapter;
import com.jersey.smartmi.fragments.view_quotes_fragment.ViewQuotesFragment;
import com.jersey.smartmi.listeners.FragmentChangeListener;
import com.jersey.smartmi.models.NavDrawerItem;
import com.jersey.smartmi.widget.MyListView;
import com.jersey.smartmi.widget.MyScrollView;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

/**
 * Created by Vishnu on 01/03/16.
 */
public class SmartMiActivityOld extends AppCompatActivity implements ObservableScrollViewCallbacks, FragmentChangeListener {
    private DrawerLayout mDrawerLayout;
    private MyListView mDrawerList;
    private MyScrollView mParallelaccess_Scrollview;
    private LinearLayout mLlParalelAccess;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_mi_old);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (MyListView) findViewById(R.id.list_slidermenu);
        mParallelaccess_Scrollview = (MyScrollView) findViewById(R.id.scroll);
        mLlParalelAccess = (LinearLayout) findViewById(R.id.llParalelAccess);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);


        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }

        mParallelaccess_Scrollview.setScrollViewCallbacks(this);

    }


    @Override
    public void callQuoteDetails(String from, String quote_Id, Fragment toFragment) {

    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            mDrawerLayout.closeDrawer(mParallelaccess_Scrollview);
            displayView(position);
        }
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

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            //   setTitle(navMenuTitles[position]);
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
    public void onScrollChanged(int y, boolean b, boolean b1) {
        ViewHelper.setTranslationY(mLlParalelAccess, y / 2);


    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {


    }
}
