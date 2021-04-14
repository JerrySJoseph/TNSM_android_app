package com.example.tnsm_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.js_auth.JSCloudAuth;
import com.example.tnsm_app.ui.ProfileActivity;
import com.example.tnsm_app.ui.WelcomeScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(JSCloudAuth.getInstance().getCurrentUser()==null)
        {
            startActivity(new Intent(this, WelcomeScreen.class));
            finish();
        }
        else
            startActivity(new Intent(this, ProfileActivity.class));


    }
}