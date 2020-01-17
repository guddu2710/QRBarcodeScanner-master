package com.pitangent.project.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.pitangent.project.R;
import com.pitangent.project.ui.dashboard.Dashboard;
import com.pitangent.project.ui.login.LoginActivity;
import com.pitangent.project.utils.SharedPreferencesManager;

public class SplashScreen extends AppCompatActivity {
    public static final int delaytime=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SharedPreferencesManager.getInstance().getLogged()!=null&&SharedPreferencesManager.getInstance().getLogged().equalsIgnoreCase("logged")){
                    Intent splashIntent=new Intent(com.pitangent.project.ui.splash.SplashScreen.this, Dashboard.class);
                    startActivity(splashIntent);
                    finish();
                }
                else {
                    Intent splashIntent=new Intent(com.pitangent.project.ui.splash.SplashScreen.this, LoginActivity.class);
                    startActivity(splashIntent);
                    finish();
                }


            }


        },delaytime);
    }
}
