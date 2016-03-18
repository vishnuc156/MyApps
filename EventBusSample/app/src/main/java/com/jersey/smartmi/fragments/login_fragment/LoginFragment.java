package com.jersey.smartmi.fragments.login_fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.jersey.smartmi.R;
import com.jersey.smartmi.activities.SmartMiActivity;
import com.jersey.smartmi.appcontroller.AppController;
import com.jersey.smartmi.models.login_response_parser.LoginResponse;
import com.jersey.smartmi.networkengine.OwnerRequestManager;
import com.jersey.smartmi.utils.UtilClass;
import com.squareup.otto.Subscribe;

/**
 * Created by inapp on 23/02/16.
 */
public class LoginFragment extends Fragment {

    private EditText edtUsername, edtPassword;
    private Button btnSubmit;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setUpData();
        setOnClickListeners();
    }

    public void initView(View view){
        edtUsername = (EditText)view.findViewById(R.id.edtUsername);
        edtPassword = (EditText)view.findViewById(R.id.edtPassword);
        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
    }

    public void setUpData(){

    }

    public void setOnClickListeners(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs()){
                    OwnerRequestManager.getInstance(getActivity()).requestLogin("username", "password");
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        AppController.getEventBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppController.getEventBus().register(this);

    }

    @Subscribe
    public void getLoginResponse(LoginResponse loginResponse){
        Log.i("SMARTMI========> ", loginResponse.toString());

        Intent intent = new Intent(getActivity(), SmartMiActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void getVolleyErrorResponse(VolleyError volleyErrorResponse){
//        Log.i("volleyErrorResponse==> ",volleyErrorResponse.getMessage());
    }

    private boolean checkInputs() {
        UtilClass.hideSoftKeyboard(getActivity());
        boolean flag = true;
        if (edtUsername == null || edtUsername.getText().toString().trim().length() <= 0 ) {
            flag = false;
//            YoYo.with(Techniques.Shake).playOn(edtUsername);
            return flag;
        }
        if (edtPassword == null || edtPassword.getText().toString().trim().length() <= 0 ) {
            flag = false;
//            YoYo.with(Techniques.Shake).playOn(edtPassword);
            return flag;
        }

        return flag;
    }

}

