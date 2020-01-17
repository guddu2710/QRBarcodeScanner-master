package com.pitangent.assettracking.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.pitangent.assettracking.R;
import com.pitangent.assettracking.ui.dashboard.Dashboard;
import com.pitangent.assettracking.ui.login.LoginActivity;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowSnakbar;


public class SplashScreen extends AppCompatActivity {
    public static final int delaytime=5000;
    ShowSnakbar showSnakbar=new ShowSnakbar();
    ConnectionCheck connectionCheck=new ConnectionCheck();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        Handler handler=new Handler();

        if(connectionCheck.ConnectionCheck(SplashScreen.this)){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(SharedPreferencesManager.getInstance().getLogged()!=null&&SharedPreferencesManager.getInstance().getLogged().equalsIgnoreCase("logged")){
                        Intent splashIntent=new Intent(SplashScreen.this, Dashboard.class);
                        startActivity(splashIntent);
                        finish();
                    }
                    else {
                        Intent splashIntent=new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(splashIntent);
                        finish();
                    }


                }


            },delaytime);
        }
        else {
            showSnakbar.show(SplashScreen.this,"Check your internet connection");
        }


    }
}
