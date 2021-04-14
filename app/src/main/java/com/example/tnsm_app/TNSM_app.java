package com.example.tnsm_app;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.js_auth.JSCloudAuth;
import com.example.jscloud_core.JSCloudApp;
import com.example.jscloud_core.interfaces.CloudServerConnectionCallback;

public class TNSM_app extends Application {
    String TAG="TNSM-APP";
    @Override
    public void onCreate() {
        super.onCreate();
        //Setting callback for error debugging
        JSCloudApp.setCloudServerConnectionCallback(mCloudConnectionCallback);

        //Note only initialize the APP after setting all properties
        JSCloudApp.init(getApplicationContext(),"http://192.168.1.26:3001");

        JSCloudAuth.setGoogleClientID("162441073530-raoqmrk7bka1s3kr8o1o58j0tktrlrvh.apps.googleusercontent.com");
    }

    CloudServerConnectionCallback mCloudConnectionCallback= new CloudServerConnectionCallback() {
        @Override
        public void onConnected() {
            Log.d(TAG,"Device connected to Server");
         //   Toast.makeText(getApplicationContext(),"Connected to server",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected() {
            Log.d(TAG,"Device Disconnected from server");
         //   Toast.makeText(getApplicationContext(),"Disconnected from server",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onConnectionFailed(String reason) {
            Log.d(TAG,reason);
        //    Toast.makeText(getApplicationContext(),reason,Toast.LENGTH_SHORT).show();
        }
    };
}
