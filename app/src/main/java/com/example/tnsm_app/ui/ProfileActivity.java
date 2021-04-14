package com.example.tnsm_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.js_auth.JSCloudAuth;
import com.example.js_auth.interfaces.SignOutResponse;
import com.example.tnsm_app.R;
import com.example.tnsm_app.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    TextView name,followers;
    CircleImageView profileImg;
    ImageView more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        more=findViewById(R.id.more);
        profileImg=findViewById(R.id.profile_image);
        name=findViewById(R.id.name);
        followers=findViewById(R.id.followers);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getApplicationContext(),more);
                popupMenu.getMenuInflater().inflate(R.menu.profile_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.menu_logout)
                            logout();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });


        loadData();

    }
    void logout()
    {
        JSCloudAuth.getInstance().signOut(new SignOutResponse() {
            @Override
            public void onSignOutSuccess() {
                Toast.makeText(getApplicationContext(),"user signed out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),WelcomeScreen.class));
                finish();
            }

            @Override
            public void onSignOutFailed(String reason) {
                Toast.makeText(getApplicationContext(),reason,Toast.LENGTH_SHORT).show();
            }
        });
    }

    void loadData()
    {

        if(JSCloudAuth.getInstance().getCurrentUser()!=null)
        {
            User user=JSCloudAuth.getInstance().getCurrentUser(User.class);
            name.setText(user.getName());

            Glide.with(profileImg).load(user.getPhotoUrl()).into(profileImg);
        }
        else
        {
            startActivity(new Intent(this,WelcomeScreen.class));
            finish();
        }
    }

}