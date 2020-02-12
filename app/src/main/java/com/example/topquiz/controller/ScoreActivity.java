package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.topquiz.R;
import com.example.topquiz.Views.ScoreDisplayAdapter;
import com.example.topquiz.model.AlphabeticComparator;
import com.example.topquiz.model.HighScoreComparator;
import com.example.topquiz.model.ListUserCreator;
import com.example.topquiz.model.User;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;

public class ScoreActivity extends AppCompatActivity {

    private RecyclerView mListScore;

    private Button mHighScore;
    private Button mAlphabetic;

    public ListUserCreator mListUserCreator; // Celle ci
    public SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        System.out.println("ScoreActivity::OnCreate()");

        mListScore = (RecyclerView) findViewById(R.id.recycler_view_score_activity);
        mHighScore = (Button) findViewById(R.id.activity_score_high_btn);
        mAlphabetic = (Button) findViewById(R.id.activity_score_alphabetic_btn);

        pref = getSharedPreferences("Top_Quiz", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(MainActivity.PREF_KEY_LISTUSERCREATOR, "");
        mListUserCreator = gson.fromJson(json, ListUserCreator.class);
        DisplayScore(new HighScoreComparator());

        mHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayScore(new HighScoreComparator());
            }
        });

        mAlphabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayScore(new AlphabeticComparator());
            }
        });
    }

   public void DisplayScore(Comparator<User> userComparator) {
       Gson gson = new Gson();
       Log.e("List avant tri", gson.toJson(mListUserCreator.getUserList()));
       Collections.sort(mListUserCreator.getUserList(), userComparator);
       Log.e("List après tri", gson.toJson(mListUserCreator.getUserList()));
    }
    //TODO : + autre méthode DisplayScore cette fois sans le param pour ne pas le trier par défaut. juste dans l'ordre d'arrivée.
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
