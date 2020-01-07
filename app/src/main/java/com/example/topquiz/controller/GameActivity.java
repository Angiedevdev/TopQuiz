package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.TestLooperManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestionTxt;
    private Button mAnswer1Button, mAnswer2Button, mAnswer3Button, mAnswer4Button;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("GameActivity::OnCreate()");
        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 15;
        }
        mEnableTouchEvents = true;

        mQuestionTxt = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswer1Button = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswer2Button = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswer3Button = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswer4Button = (Button) findViewById(R.id.activity_game_answer4_btn);

        mAnswer1Button.setTag(0);
        mAnswer2Button.setTag(1);
        mAnswer3Button.setTag(2);
        mAnswer4Button.setTag(3);

        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }
    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            Toast.makeText(this, "Bien !!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Loupé!", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if (--mNumberOfQuestions == 0) {
                    endGame();
                } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2500);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }
    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("C'est fini ! ")
                .setMessage("Ton score est de " + mScore)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
    private void displayQuestion(final Question question){
        mQuestionTxt.setText(question.getQuestion());
        mAnswer1Button.setText(question.getChoiceList().get(0));
        mAnswer2Button.setText(question.getChoiceList().get(1));
        mAnswer3Button.setText(question.getChoiceList().get(2));
        mAnswer4Button.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestions(){
        Question question1 = new Question("Quel est le nom du Président en 2020 ?",
                                          Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                                          1);
        Question question2 = new Question("Quel est le prénom de la Belle aux Bois Dormant",
                                          Arrays.asList("Aurore", "Blanche", "Emma", "Cindy"),
                                          0);
        Question question3 = new Question("De quelle couleur sont les chaussettes de l'Archiduchesse ? ",
                                          Arrays.asList("roses", "blanches", "oranges", "j'en sais rien"),
                                          3);
        Question question4 = new Question("Laquelle n'est pas une couleur primaire ? ",
                                          Arrays.asList("jaune", "bleue", "verte", "rouge"),
                                          2);
        Question question5 = new Question("Où se couche le soleil ? ",
                                          Arrays.asList("Nord", "Sud", "Est", "Ouest"),
                                          3);
        Question question6 = new Question("Trouve l'intru : ",
                                          Arrays.asList("temps", "minute", "micron", "centimètre"),
                                          0);
        Question question7 = new Question("Qu'est-ce qui est le plus grand ? ",
                                          Arrays.asList("un continent", "une planète", "un village", "un moulin à vent"),
                                          0);
        Question question8 = new Question("Qui chante il en faut peu pour être heureux ? ",
                                          Arrays.asList("Baguerra", "Baloo", "Bouh", "Olaf"),
                                          1);
        Question question9 = new Question("Quel est l'animal terrestre le plus rapide ? ",
                                          Arrays.asList("l'antilope", "le guépard", "le rinhocéros", "l'autruche"),
                                          1);
        Question question10 = new Question("Comment dit-on Bonjour en espagnol ? ",
                                          Arrays.asList("Hallo", "Hello", "Ni Hao", "Hola"),
                                          3);
        Question question11 = new Question("Comment s'appelle un livre d'une série de livre ? ",
                                          Arrays.asList("tome", "épisode", "saison", "chapitre"),
                                          0);
        Question question12 = new Question("Grâce à quoi on peut se connecter à Internet sans fil ? ",
                                          Arrays.asList("Bluetooth", "Ethernet", "Wifi", "Télépathie"),
                                          2);
        Question question13 = new Question("Combien de pays existe-il en Europe ? ",
                                           Arrays.asList("6", "12", "28", "32"),
                                           2);
        Question question14 = new Question("Sur quel continent se trouve le Canada ? ",
                                           Arrays.asList("Afrique", "Amérique", "Océanie", "Asie"),
                                           1);
        Question question15 = new Question("Comment s'appelle l'explorateur qui à découvert l'Amérique ? ",
                                           Arrays.asList("Christophe Collomb", "Gérard Collomb", "Amerigo Vespucci", "Vasco de Gama"),
                                           2);
        Question question16 = new Question("Quel personnage est un personnage Disney? ",
                                           Arrays.asList("Tintin", "les Schtroumps", "les Trolls","Oswald"),
                                           3);
        Question question17 = new Question("Lequel est un code de programmation ? ",
                                           Arrays.asList("Python", "Crotal", "Vipère", "Orvet"),
                                           0);
        Question question18 = new Question("Comment s'appelle le fils dans les Simpsons? ",
                                           Arrays.asList("Karl", "Bart", "Mart", "Marg"),
                                           1);
        Question question19 = new Question("Quelle ville ne retrouve-t-on pas dans un gâteau ? ",
                                           Arrays.asList("Paris", "Brest", "Marseille", "St Tropez"),
                                           2);
        Question question20 = new Question("Quel dessin animé tes parents regardaient-ils quand ils avaient ton âge ? ",
                                           Arrays.asList("Olive et Tom", "Pat'Patrouille", "PollyPocket", "C'est pas sorcier" ),
                                           0);
        return new QuestionBank(Arrays.asList(question1,
                                              question2,
                                              question3,
                                              question4,
                                              question5,
                                              question6,
                                              question7,
                                              question8,
                                              question9,
                                              question10,
                                              question11,
                                              question12,
                                              question13,
                                              question14,
                                              question15,
                                              question16,
                                              question17,
                                              question18,
                                              question19,
                                              question20));
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("GameActivity::OnStart()");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("GameActivity::OnResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("GameActivity::OnPause()");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("GameActivity::OnStop()");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("GameActivity::OnDestroy()");
    }
}