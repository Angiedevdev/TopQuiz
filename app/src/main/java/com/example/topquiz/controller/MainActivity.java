package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.ListUserCreator;
import com.example.topquiz.model.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mScoreButton;

    public ListUserCreator mScoreDisplay;
    public User mUser;

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    public static final String PREF_KEY_LISTUSERCREATOR = "PREF_KEY_LISTUSERCREATOR";

    public static final int GAME_ACTIVITY_REQUEST_CODE = 10;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences("Top_Quiz", MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mScoreButton = (Button)findViewById(R.id.activity_main_score_btn);

        mPlayButton.setEnabled(false);
        mScoreButton.setEnabled(false);

        greetUser();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() !=0);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String firstname = mNameInput.getText().toString();
            mUser = new User();
            mUser.setFirstName(firstname);

            mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();

            Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
            startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent scoreActivityIntent = new Intent(MainActivity.this, ScoreActivity.class);
            startActivity(scoreActivityIntent);
            }
        });
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mUser.setScore(score);
            mPreferences.edit().putInt(PREF_KEY_SCORE, mUser.getScore()).apply();
            greetUser();
            Gson gson = new Gson();
            String json = mPreferences.getString(PREF_KEY_LISTUSERCREATOR, null);
            if (json == null) {
                mScoreDisplay = new ListUserCreator ();
            } else {
                mScoreDisplay = gson.fromJson(json, ListUserCreator.class);
             }
            mScoreDisplay.addUser(mUser);
            json = gson.toJson(mScoreDisplay);
            mPreferences.edit().putString(PREF_KEY_LISTUSERCREATOR, json).apply();

            //TODO DU FUTUR : apres recup scor dis le user actuel verifier si score fait parti des top 5, alors enregistrer et suppri l'index 5
        }
    }

    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
            String accrochetexte = "Ravie de te revoir, " + firstname
                    + "!\nTon dernier est score est de " + score
                    + ", feras-tu mieux cette fois ?";
            mGreetingText.setText(accrochetexte);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
            mScoreButton.setEnabled(true);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("MainActivity::OnStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("MainActivity::OnResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("MainActivity::OnPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("MainActivity::OnStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("MainActivity::OnDestroy()");
    }
}