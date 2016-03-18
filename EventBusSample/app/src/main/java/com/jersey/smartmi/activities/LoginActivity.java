package com.jersey.smartmi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jersey.smartmi.R;
import com.jersey.smartmi.fragments.login_fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        loadFragment(savedInstanceState);
    }

    private void loadFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).commit();
        }
    }


}
