package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 12/8/15.
 */



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //thread for splash screen running
        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    Log.d("Exception", "Exception" + e);
                } finally {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
                finish();
            }
        };
        logoTimer.start();
    }

}

