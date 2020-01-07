package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.topquiz.R;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("ScoreActivity::OnStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("ScoreActivity::OnResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("ScoreActivity::OnPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("ScoreActivity::OnStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("ScoreActivity::OnDestroy()");
    }
}
