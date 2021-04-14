package com.example.tnsm_app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.js_auth.JSCloudAuth;
import com.example.js_auth.JSCloudAuthActivity;
import com.example.js_auth.Models.JSCloudUser;
import com.example.tnsm_app.MainActivity;
import com.example.tnsm_app.R;
import com.example.tnsm_app.models.User;

public class RegisterActivity extends JSCloudAuthActivity {

    EditText name,email,password,cpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);

    }

    private User prepareUserFromInputFields()
    {
        String pass=password.getText().toString().trim();
        String cpass=cpassword.getText().toString().trim();
        String _email=email.getText().toString().trim();
        String _name=name.getText().toString().trim();
        if(_email==null ||_email.isEmpty())
        {
            email.setError("Email must not be empty");
            return null;
        }
        if(_name==null ||_name.isEmpty())
        {
            name.setError("Name must not be empty");
            return null;
        }
      /*  if(pass.isEmpty() || cpass.isEmpty() || pass!=cpass)
        {
            password.setError("Password and Confirm password must match and should not be empty");
            cpassword.setError("Password and Confirm password must match and should not be empty");
            return null;
        }*/
        User user= new User();
        user.setEmail(_email);
        user.setName(_name);
        //NOTE: Hash Password or do some encryption on the client side
        user.setPassword(pass);
        return user;
    }
    public void onEmailRegisterClick(View view) {
        User user=prepareUserFromInputFields();
        if(user==null)
            return;
        JSCloudAuth.getInstance().createUser(this,user);
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, WelcomeScreen.class));
        finish();
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
}