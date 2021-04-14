package com.example.tnsm_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.js_auth.JSCloudAuth;
import com.example.js_auth.JSCloudAuthActivity;
import com.example.js_auth.Models.JSCloudUser;
import com.example.tnsm_app.MainActivity;
import com.example.tnsm_app.R;

public class WelcomeScreen extends JSCloudAuthActivity {

    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
    }

    public void onGoogleLoginClick(View view) {
        Log.e("CLick","google signInClick");
        JSCloudAuth.getInstance().signInWithGoogle(this);
    }

    public void onEmailLoginClick(View view) {
        String _email=email.getText().toString().trim();
        String _pass=password.getText().toString().trim();
        JSCloudAuth.getInstance().signInWithEmail(this,_email,_pass);
    }

    @Override
    protected void onAuthResponse(String responseMessage, JSCloudUser user) {
        Toast.makeText(this,responseMessage,Toast.LENGTH_SHORT).show();
        if(user!=null)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}