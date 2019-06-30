package com.example.medkit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class Confirmed extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_confirmed);

        progressBar= findViewById(R.id.MedReminderProgressBarId);

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                GoToHome();
            }
        });
        thread.start();


    }

    public void doWork(){

        for(progress=20;progress<=100;progress=progress+20){

            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void GoToHome(){

        Intent intent=new Intent(Confirmed.this,NavigationView.class);
        startActivity(intent);
        finish();
    }


}
