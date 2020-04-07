package com.example.topquiz.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    public ListUserCreator mListUserCreator;
    public SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mListScore = (RecyclerView) findViewById(R.id.recycler_view_score_activity);
        mHighScore = (Button) findViewById(R.id.activity_score_high_btn);
        mAlphabetic = (Button) findViewById(R.id.activity_score_alphabetic_btn);

        pref = getSharedPreferences("Top_Quiz", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(MainActivity.PREF_KEY_LISTUSERCREATOR, "");
        mListUserCreator = gson.fromJson(json, ListUserCreator.class);
        displayScore(new HighScoreComparator());
        configureScoreDisplayRecyclerView();

        mHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(new HighScoreComparator());
                configureScoreDisplayRecyclerView();
            }
        });

        mAlphabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayScore(new AlphabeticComparator());
                configureScoreDisplayRecyclerView();
            }
        });
    }

    public void displayScore(Comparator<User> userComparator) {
       Collections.sort(mListUserCreator.getUserList(), userComparator);
    }

    public void configureScoreDisplayRecyclerView(){
        this.mListScore.setAdapter(new ScoreDisplayAdapter(this.mListUserCreator.getUserList()));
        this.mListScore.setLayoutManager(new LinearLayoutManager(this));
    }
}