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
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mScoreButton;
    public User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 10;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity::onCreate()");
        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);
        //Rajout ici
        mScoreButton = (Button)findViewById(R.id.activity_main_score_btn);
        mScoreButton.setEnabled(false);
        //Fin rajout ici
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
                mUser.setFirstName(firstname);
                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        //Rajout ici
        //Récupérer ici le score que j'aurai d'abord récupérer dans score activité
           // if (score >= 1) { voir avec un listView et keyevent
                mScoreButton.setEnabled(true);
                mScoreButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent scoreActivityIntent = new Intent(MainActivity.this, ScoreActivity.class);
                        startActivity(scoreActivityIntent);
                    }
                });
            //}
        //Fin rajout ici
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            greetUser();
        }
    }
    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
            String fulltext = "Ravie de te revoir, " + firstname
                    + "!\nTon dernier est score est de " + score
                    + ", feras-tu mieux cette fois ?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
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